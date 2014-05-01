package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.google.common.hash.Hashing;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
              
        HashMap<DistributedCacheService, Integer> Circle = new HashMap<DistributedCacheService, Integer>();
        
        Circle.put(new DistributedCacheService("http://localhost:3000"), 0);
        Circle.put(new DistributedCacheService("http://localhost:3001"), 1);
        Circle.put(new DistributedCacheService("http://localhost:3002"), 2);
       
              
        int totallength = Circle.size();
        
        
        
        for (int z=1; z<=10; z++){
        	
        	int bucket = Hashing.consistentHash( Hashing.md5().hashInt(z), totallength);
         
            
            for (Integer value : Circle.values()) {
            	if ( bucket == value) {
            	String[] str ={"0", "a", "b", "c", "d", "e","f", "g", "h", "i", "j",};
            	            	
            	
            	Iterator<Map.Entry<DistributedCacheService, Integer>> entries = Circle.entrySet().iterator();
                while (entries.hasNext()) {
                	
                	
                    Map.Entry<DistributedCacheService, Integer> entry = entries.next();
                    if ( bucket == entry.getValue()) {              
                    
                    entry.getKey().put(z, str[z]);
                    

                    String  objective = entry.getKey().get(z);
                    System.out.println("get(" + z + ") => " + objective);
                    
                    }
                }
                
            	}
            }       	
            }
         
        

        System.out.println("Existing Cache Client...");
    }

}