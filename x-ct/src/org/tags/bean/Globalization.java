package org.tags.bean;

import java.util.HashMap;
import java.util.Map;

public class Globalization {
    private static Map<String, MasterData> local = new HashMap<String, MasterData>();
    
    public static String getI18ndesc(String lang, String key){
        if( local.isEmpty() ){
            return "";
        }
        MasterData value = local.get(lang);
        if( value == null ){
            return "";
        }
        String label = value.getI18ndesc(key);
        return label;
    }
    
    public static void put(String lang, MasterData data){
        local.put(lang, data);
    }
    
}
