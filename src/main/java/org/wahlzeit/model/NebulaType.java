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
