package org.jnape;

import org.junit.Before;
import org.junit.Test;
import testsupport.Pojo;

public class ReflectedObjectTest {

    private Pojo pojo;

    @Before
    public void setUp() {
        pojo = new Pojo();
    }

    @Test
    public void shouldConstruct() {
        new ReflectedObject<Pojo>(pojo);
    }
}
