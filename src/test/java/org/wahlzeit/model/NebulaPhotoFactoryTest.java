package org.wahlzeit.model;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NebulaPhotoFactoryTest {
    @ClassRule
    public static RuleChain ruleChain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    @Test
    public void testMagnitudeConstructor() {
        NebulaPhotoFactory nebulaPhotoFactory = NebulaPhotoFactory.getInstance();
        assertTrue(nebulaPhotoFactory.getClass().getName() == NebulaPhotoFactory.class.getName());

        NebulaPhoto nebulaPhoto = nebulaPhotoFactory.createPhoto(new PhotoId(1), 5.5);
        assertEquals(nebulaPhoto.getId().asInt(), 1);
    }
}
