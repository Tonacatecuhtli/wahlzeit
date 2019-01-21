package org.wahlzeit.model;

import org.junit.Test;

public class NebulaTest {

    @Test
    public void createNebulaType(){
        NebulaManager nebulaManager = NebulaManager.getInstance();
        NebulaType nt1 = nebulaManager.createNebulaType("H II Region");
    }
}
