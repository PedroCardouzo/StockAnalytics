# 29/04/2018 - Pedro Cardouzo
	This program will be developed in modules:
		API_request module
		data_analysis module (functional and object oriented)
		graphing module
		* additional modules might be necessary, like command line interface module, but the most important modules are the 3 mentioned
		* more information about each module can be obtained in Readme.md
	At the point where each module is working correctly, the folder structure might be rearranged. Integration phase will begin.

	@ Progress
 
		Made the haskell server instance. It was more of a proof of concept to see if it was possible to use it instead of just passing the data through files.
		Tested it using python client and it seemed to work fine.

	@ next step 
		As far as right know, input will be a list of stock market codes, the types of analysis that will be done on the data and the interval of the data (might not be necessary for some data-analysis, but necessary for others) and the returning value will be in the format (header, list_of_values). However, this format may be implicit though the order that the lists come back (will need to see at the implementation phase)

		decide what data analysis algorithms will be implemented (I've already thought of some, but It's not a final list) so that I can better determine how the information will need to be delivered to the haskell server.

		(Of course, planning was done on a white board and there was a lot of thought about what information will need to be delivered for the current algorithms, what will come back and in what format, but this still needs to be more clear)

