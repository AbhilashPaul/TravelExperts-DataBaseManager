package travelexperts.models;

import java.util.HashMap;

//this class is utilises a hashmap to handle data transitions between agency name and id
public class AgenciesUtil {

    //this method creates a hashmap with agency id as key and agency name as value
    public static HashMap<Integer, String> createAgencyList(){
        HashMap<Integer, String> agencies = new HashMap<Integer, String>();
        agencies.put(1,"Calgary");
        agencies.put(2,"Okotoks");
        return agencies;
    }

    //retrieve agency id using agency name
    public static <K,V> K getAgencyId(HashMap<K,V> hashmap, V value){
        for (HashMap.Entry<K,V> entry : hashmap.entrySet()){
            if( value.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }
    //retrieve agency id using agency name
    public static boolean contains(HashMap<Integer,String> hashmap, String value){
        for (HashMap.Entry<Integer,String> entry : hashmap.entrySet()){
            if( value.equals(entry.getValue())){
                return true;
            }
        }
        return false;
    }
}
