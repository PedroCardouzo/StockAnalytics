import Network.MessagePack.Server

func :: [(Int, Int, Int)] -> Server [Int]
func list = return $ map (\(x, y, z) -> x + y + z) list

startServer :: Int -> IO ()
startServer port = serve port [ (method "func" func) ]

-- client = msgpackrpc.Client(msgpackrpc.Address(hostAddr, port)) -- must be same port as server, host will usually be localhost
-- client.call('func', [1,2,3,4])
-- client will print "10"
-- works for multiple arguments aswell: client.call('func', x, y, ...) will be matched by func x y ... = return $ (code body)
