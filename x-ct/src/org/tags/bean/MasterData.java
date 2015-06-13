package org.tags.bean;

import java.util.HashMap;
import java.util.Map;

public class MasterData {
    private Map<String, String> local = new HashMap<String, String>();
    
    public void put(String key, String value){
        local.put(key, value);
    }
    
    public String getI18ndesc(String key){
        if( local.isEmpty() ){
            return "";
        }
        return local.get(key);
    }
    
}
