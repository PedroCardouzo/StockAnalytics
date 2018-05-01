This program consists on .. (too lazy now will do this later)


There are 3 main modules:
# main program -> (integration, not a module)
	This will call every one of the three modules. It is the integration between all modules.

# API request module -> 
	Does the requests to the API, as well as any necessary formatting and selection of what kind of data analysis will be done

# Data Analysis module ->
	will process the data in various forms.

# Graphing module ->
	will help visualize the results by graphing the data.

# Integration between API request, Data Analysis and Graphing modules
API request module and Graphing module will be implemented exclusively in Java, therefore the integration between these two modules and the Data Analysis module will differ between the functoinal version and the object oriented version.


# Funcional Implementation :: 
	at the end of the API request module the integration class will call a haskell server with the data to process. The results will come back as a response and passed to the graphing module

# Object Oriented Implementation :: 
	at the end of API request module, the Data Analysis module will be called directly and it's results will be passed to the graphing module

Keep in mind that, abstracting the fact that there will be a remote function call to a haskell server, the integration should follow the same pattern and behaviour
