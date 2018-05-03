import Network.MessagePack.Server

func :: [Int] -> Server Int
func list  = return $ map _func list

startServer :: Int -> IO ()
startServer port = serve port [ (method "func" func) ]

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
