package utils;

public class DataSingleton {
	
	private static DataSingleton single_instance = null; 
	  
    public String connectionString; 
  

    private DataSingleton() 
    { 
       
    } 
  

    public static DataSingleton getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new DataSingleton(); 
  
        return single_instance; 
    } 

}
