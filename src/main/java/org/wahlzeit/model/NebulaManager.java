package org.wahlzeit.model;

import java.util.HashMap;
import java.util.HashSet;

public class NebulaManager {

    private final NebulaManager instance = new NebulaManager();
    private HashMap<Integer, Nebula> nebulaHashMap = new HashMap<>();
    private HashMap<String, NebulaType> nebulaTypes = new HashMap<>();

    /**
     * @methodType getter
     */
    public NebulaManager getInstance(){
        return instance;
    }
    /**
     * @methodtype factory
     */
    public Nebula createNebula(String typeName, Integer discoverTimestamp, Location location){

        NebulaType nt = nebulaTypes.get(typeName);
        Nebula result = nt.createInstance(discoverTimestamp, location);

        if(nebulaHashMap.containsKey(result.hashCode())){
            return nebulaHashMap.get(result.hashCode());
        }

        nebulaHashMap.put(result.hashCode(), result);
        return result;
    }
    /**
     * @methodtype factory
     */
    public NebulaType createNebulaType (String typeName){
        NebulaType nt = new NebulaType(typeName);

        if(nebulaTypes.containsKey(typeName)){
            return nebulaTypes.get(typeName);
        }

        nebulaTypes.put(typeName, nt);
        return nt;
    }
}
