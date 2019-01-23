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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NebulaType {
    private String name; // e.g. H II Region, Planetary, Supernova remnant, Dark nebula, Diffuse nebula

    private NebulaType superType = null;
    private Set<NebulaType> subTypes = new HashSet<NebulaType>();
    /**
     * @methodType constructor
     */
    public NebulaType(String name){
        this.name = name;
    }
    /**
     * @methodType factory
     */
    public Nebula createInstance(Integer discoverTimestamp, Location location) {
        return new Nebula(this, discoverTimestamp, location);
    }

    /**
     * @methodType getter
     */
    public String getName() {
        return name;
    }

    /**
     * @methodType getter
     */
    public NebulaType getSuperType() {
        return superType;
    }
    /**
     * @methodType setter
     */
    private void setSuperType(NebulaType nt){
        superType = nt;
    }

    public Iterator<NebulaType> getSubTypeIterator(){
        return subTypes.iterator();
    }
    /**
     * @methodType setter, primitive
     */
    public void addSubType(NebulaType nt){
        assert (nt != null): "tried to set null sub-type";
        nt.setSuperType(this);
        this.subTypes.add(nt);
    }
    /**
     * @methodType query
     */
    public boolean hasInstance(Nebula nebula){
        assert (nebula != null): "asked about null object";

        if(nebula.getType() == this){
            return true;
        }

        for(NebulaType type : subTypes){
            if(type.hasInstance(nebula)){
                return true;
            }
        }

        return false;
    }

    /**
     * @methodType query
     */
    public boolean isSubtype () {
        return superType != null;
    }
}
