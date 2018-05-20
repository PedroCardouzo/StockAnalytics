import System.Environment
import Network.MessagePack.Server
import Control.Parallel.Strategies
import Data.Function

-- Compile : ghc -threaded <name-of-this-file> -o <name-of-executable-file>
-- Run : ./<name-of-executable-file> +RTS -Nx | x is an integer -> number of cores to use, if x is blank it will use all cores

main :: IO()
main = do
    [arg] <- getArgs
    startServer (read arg::Int)


-- API_list :: [(method "<Remote Function Call name>" <haskell_method_name>)]
--let _API_list = 

--f :: Int -> Server Int
f x = return $ x+1

startServer :: Int -> IO()
startServer port = serve port [(method "simpleMovingAverage" parallelSMA), (method "exponentialMovingAverage" parallelEMA), (method "rsi" parallelRSI), (method "obv" parallelOBV)]
-- (ps.: seems like for using it in the list above they need to have strict types and not type constructors: cannot use generic [a] with Fractional context, must use [Double] directly, for example)


-- API (parallelized) -> 

parallelSMA :: Int -> [[Double]] -> Server [[Double]]
parallelSMA size list = return $ parMap rpar (simpleMovingAverage size) list

parallelEMA :: Int -> [[Double]] -> Server [[Double]]
parallelEMA size list = return $ parMap rpar (exponentialMovingAverage size) list

parallelRSI :: Int -> [[Double]] -> Server [[Double]]
parallelRSI period list = return $ parMap rpar (rsi period) list

parallelOBV :: [[Double]] -> [[Double]] -> Server [[Double]]
parallelOBV close volume = return $ parMap rpar f (zip close volume)
    where
        f = \(x,y) -> obv x y

-- API (internal) is what each function will call in parallel

simpleMovingAverage :: (Fractional a) => Int -> [a] -> [a]
simpleMovingAverage = makeMovingAverage sma

exponentialMovingAverage :: (Fractional a) => Int -> [a] -> [a]
exponentialMovingAverage = makeMovingAverage ema

rsi :: (Fractional a, Ord a) => Int -> [a] -> [a]
rsi period list = map (\x -> _rsi (delta x) 0 0) (splitEvery period list)

obv :: (Num a, Ord a1) => [a1] -> [a] -> [a]
obv close volume = reverse $ _obv (reverse close) (reverse volume) 0

-- API helpers

makeMovingAverage :: ([a] -> b) -> Int -> [a] -> [b]
makeMovingAverage func size list
    | length list <= size = [func list]
    | otherwise           = func (take size list) : makeMovingAverage func size (tail list)


sma :: Fractional a => [a] -> a
sma list = (/) (foldl (+) 0 list) (fromIntegral $ length list)



ema :: Fractional a => [a] -> a
ema list = _ema list k l_sma
    where 
        k = ((/) `on` fromIntegral) 2 (length list)
        l_sma = sma list

_ema :: Num a => [a] -> a -> a -> a
-- Complexity : O(n)
_ema [] _ l_sma = l_sma
_ema (h:t) k l_sma = (h-yest_ema)*k + yest_ema
    where yest_ema = _ema t k l_sma

splitEvery :: Int -> [t] -> [[t]]
splitEvery _ [] = []
splitEvery n list = first : (splitEvery n rest)
  where
    (first,rest) = splitAt n list


_rsi :: (Fractional a, Ord a) => [a] -> a -> a -> a
_rsi [] 0 0 = 0.0 -- in case _rsi is called with an empty list from rsi
_rsi [] up down = up/(up-down) -- up - down because down was negative
_rsi (h:t) up down
    | h >= 0 = _rsi t (h+up) down
    | otherwise = _rsi t up (h+down)


delta :: Num a => [a] -> [a]
delta (h1:h2:t) = h1-h2 : delta (h2:t)
delta [a] = []
delta [] = [] -- shouldn't occur but is here if someone calls 'delta []'


_obv :: (Num t, Ord a) => [a] -> [t] -> t -> [t]
_obv _ (_:[]) _ = []
_obv (_:[]) _ _ = []
-- cy: close_yesterday  ct: close_today  vt: volume_today
_obv (cy:ct:tclose) (_:vt:tvol) acc
    | ct > cy = f (acc+vt)
    | ct < cy = f (acc-vt)
    | otherwise = f acc
        where 
            f x = x : _obv (ct:tclose) (vt:tvol) x

--polyfit :: (Enum a, Eq a, Fractional a) => [a] -> [a]
-- polyCoeffs LE list -> converts list type from Poly a to [a] 
-- | LE is to flag that it is little endian (head of list is smaller coeficient of polynomial)
--polyfit list = polyCoeffs LE (lagrangePolyFit $ zip [0..] list)



-- client = msgpackrpc.Client(msgpackrpc.Address(hostAddr, port)) -- must be same port as server, host will usually be localhost
-- client.call('func', [1,2,3,4])
-- client will print "10"
-- works for multiple arguments aswell: client.call('func', x, y, ...) will be matched by func x y ... = return $ (code body)


-- each API function will do something of the sort: map func list | list may be like the following examples:
-- [FinData, FinData] <- apply function 'func' for each argument 
-- [[FinData]] <- apply function 'func' for each sublist
-- any function might have arity == 1 or arity >= 2 (for any other possible arity, like arity == 2 please discuss with the group)


data FinData = FinData { 
    symbol :: String,
    open :: [Float],
    high :: [Float],
    low :: [Float],
    close :: [Float],
    volume :: [Float]
}

