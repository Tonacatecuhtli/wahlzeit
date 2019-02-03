/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.model;

import java.util.HashMap;

public class NebulaManager {

    // Client-NebulaManager-Collaboration
    private HashMap<String, NebulaType> nebulaTypes = new HashMap<>();
    private static final NebulaManager instance = new NebulaManager();
    private HashMap<Integer, Nebula> nebulaHashMap = new HashMap<>();
    /**
     * @methodType getter
     */
    public static NebulaManager getInstance(){
        return instance;
    }

    // NebulaManager-Nebula-Collaboration
    /**
     * @methodtype factory
     */
    public Nebula createNebula(String typeName, Integer discoverTimestamp, Location location){

        NebulaType nt = nebulaTypes.get(typeName);

        if(nt == null){
            nt = createNebulaType(typeName);
        }

        Nebula result = nt.createInstance(discoverTimestamp, location);

        if(nebulaHashMap.containsKey(result.hashCode())){
            return nebulaHashMap.get(result.hashCode());
        }

        nebulaHashMap.put(result.hashCode(), result);
        return result;
    }

    // NebulaManager-NebulaType-Collaboration
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
