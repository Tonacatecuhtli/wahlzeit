package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.logging.Logger;

import static org.junit.Assert.*;


public class NebulaTest {

    private NebulaManager nebulaManager = NebulaManager.getInstance();
    private NebulaType nt;
    private NebulaType ntSub;

    private static final Logger log = Logger.getLogger(NebulaTest.class.getName());


    @Before
    public void setUp() {
        nt = nebulaManager.createNebulaType("Planetary");
        ntSub = nebulaManager.createNebulaType("Sub");
        nt.addSubType(ntSub);
    }
    @Test
    public void createNebulaType() {
        NebulaType nebulaType = nebulaManager.createNebulaType("H II Region");
    }

    @Test
    public void createNebula() throws CoordinateException {
        Coordinate coordinate = CartesianCoordinate.createCoordinate(1,2,3);
        Location location = new Location(coordinate);
        Nebula nebula = nebulaManager.createNebula("H II Region", 10, location);
    }

    @Test
    public void createNebulaTypeWithSubTypes(){
        NebulaType nt2 = nebulaManager.createNebulaType("big");
        NebulaType nt3 = nebulaManager.createNebulaType("bigger");
        nt3.addSubType(nt2);
        nt.addSubType(nt3);

        assertEquals(nt2.getSuperType().getName(), "bigger");
        assertEquals(nt3.getSuperType().getName(), "Planetary");
    }

    @Test
    public void testSubTypeIterator(){
        NebulaType ntGalaxy = nebulaManager.createNebulaType("Galaxy");
        NebulaType ntGalaxyS = nebulaManager.createNebulaType("Spiral");
        NebulaType ntGalaxyE = nebulaManager.createNebulaType("Ellipse");
        NebulaType ntGalaxySB = nebulaManager.createNebulaType("Bulge");
        NebulaType ntGalaxySBa = nebulaManager.createNebulaType("A");
        NebulaType ntGalaxySBb = nebulaManager.createNebulaType("B");
        NebulaType ntGalaxySBc = nebulaManager.createNebulaType("C");

        ntGalaxy.addSubType(ntGalaxyS);
        ntGalaxy.addSubType(ntGalaxyE);
        ntGalaxyS.addSubType(ntGalaxySB);
        ntGalaxySB.addSubType(ntGalaxySBa);
        ntGalaxySB.addSubType(ntGalaxySBb);
        ntGalaxySB.addSubType(ntGalaxySBc);

        Iterator<NebulaType> iterator = ntGalaxy.getSubTypeIterator();
        int count = 0;
        while (iterator.hasNext()){
            NebulaType level1Nt = iterator.next();
            count++;
        }
        assertEquals(count, 2);
    }

    @Test
    public void testIsSubType(){
        assertEquals(ntSub.isSubtype(), true);
    }
}
