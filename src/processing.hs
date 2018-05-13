import Data.Function
 
main :: IO()
main = do
    let close = reverse [53.26, 53.3, 53.32, 53.72, 54.19, 53.92]
    let volume = reverse [100000000, 8200, 8100, 8300, 8900, 9200]
    print $ obv close volume


-- applyPoly poly value_range = map f value_range
--    where f = makef poly
-- makef poly value = foldl (+) 0 (map (\(x,y) -> value**x * y) (zip [0..] poly))

-- polyfit :: (Enum a, Eq a, Fractional a) => [a] -> [a]
-- polyCoeffs LE list -> converts list type from Poly a to [a] 
-- | LE is to flag that it is little endian (head of list is smaller coeficient of polynom)
-- polyfit list = polyCoeffs LE (lagrangePolyFit x)
--    where
--        x = zip [0..] list

-- correlate :: (Enum c, Eq c, Fractional c) => ([c], [c]) -> [c]
-- correlate (stock_a, stock_b) = zipWith (\x -> \y -> x - y) (polynomial_derivation $ polyfit stock_a)  (polynomial_derivation $ polyfit stock_b)

-- polynomial_derivation :: (Enum c, Num c) => [c] -> [c]
-- polynomial_derivation (h:t) = zipWith (\x y -> x*y) [1..] t

-- Maybe :
macd list = ema (fst $ splitAt 12 list) - ema list

weightedMovingAverage :: (Enum a, Fractional a) => [a] -> [a]
weightedMovingAverage list = reverse $ zipWith weight [1..] (tail $ scanl (+) 0 (zipWith (*) [1..] (reverse list)))
    where weight = \x y -> 2 * y / (x*(x+1))

-- Confirmed
makeMovingAverage :: ([a] -> b) -> Int -> [a] -> [b]
makeMovingAverage func size list
    | length list <= size = [func list]
    | otherwise           = func (take size list) : makeMovingAverage func size (tail list)


simpleMovingAverage = makeMovingAverage sma

sma :: Fractional a => [a] -> a
sma list = (/) (foldl (+) 0 list) (fromIntegral $ length list)


exponentialMovingAverage = makeMovingAverage ema

ema :: Fractional a => [a] -> a
-- Complexity : O(1)
ema list = _ema list k l_sma
    where 
        k = ((/) `on` fromIntegral) 2 (length list)
        l_sma = sma list

_ema :: Num a => [a] -> a -> a -> a
-- Complexity : O(n)
_ema [] _ l_sma = l_sma
_ema (h:t) k l_sma = (h-yest_ema)*k + yest_ema
    where yest_ema = _ema t k l_sma


splitEvery _ [] = []
splitEvery n list = first : (splitEvery n rest)
  where
    (first,rest) = splitAt n list


rsi list period = map (\x -> _rsi (delta x) 0 0) (splitEvery period list)

_rsi [] 0 0 = 0.0 -- in case _rsi is called with an empty list from rsi
_rsi [] up down = up/(up-down) -- up - down because down was negative
_rsi (h:t) up down
    | h >= 0 = _rsi t (h+up) down
    | otherwise = _rsi t up (h+down)

delta (h1:h2:t) = h1-h2 : delta (h2:t)
delta [a] = []
delta [] = [] -- shouldn't occur but is here if someone calls 'delta []'



obv close volume = reverse $ _obv (reverse close) (reverse volume) 0

_obv _ (_:[]) _ = []
_obv (_:[]) _ _ = []
-- cy: close_yesterday  ct: close_today  vt: volume_today
_obv (cy:ct:tclose) (_:vt:tvol) acc
    | ct > cy = f (acc+vt)
    | ct < cy = f (acc-vt)
    | otherwise = f acc
        where 
            f x = x : _obv (ct:tclose) (vt:tvol) x