package khoavin.sillylearningenglish.Function.TrainingRoom.Storage;

import java.util.HashMap;

/**
 * Created by KhoaVin on 11/05/2017.
 */

public class Storage {
    public static Storage instance = null;
    public static HashMap<String,Object> storage;
    private Storage(){
        storage = new HashMap<>();
    }
    public static void addValue(String key, Object objects){
        storage.put(key,objects);
    }
    public static Storage getInstance(){
        if (instance == null)
        {
            instance = new Storage();
        }
        return instance;
    }
    public static Object getValue(String key){
        if (CheckNodeIsExist(key))
        return storage.get(key);
        else
            return null;
    }
    public static boolean CheckNodeIsExist(String key){

            boolean x = false;
            x =  storage.containsKey(key);
            return x;
    }
}
