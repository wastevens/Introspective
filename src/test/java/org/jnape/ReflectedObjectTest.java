package org.jnape;

import org.junit.Before;
import org.junit.Test;
import testsupport.Pojo;

import java.util.Random;

import static junit.framework.Assert.*;

public class ReflectedObjectTest {

    private Pojo pojo;
    private ReflectedObject<Pojo> reflectedObject;

    @Before
    public void setUp() {
        pojo = new Pojo();
        reflectedObject = new ReflectedObject<Pojo>(pojo);
    }

    @Test
    public void shouldKnowIfHasField() {
        assertTrue(reflectedObject.hasField("intField"));
        assertTrue(reflectedObject.hasField("stringField"));
    }

    @Test
    public void shouldKnowIfDoesNotHaveField() {
        assertFalse(reflectedObject.hasField("impossible field"));
    }

    @Test
    public void shouldGetValueOfField() {
        assertEquals(pojo.intField, reflectedObject.getValueOfField("intField"));
        assertEquals(pojo.stringField, reflectedObject.getValueOfField("stringField"));

        int newIntFieldValue = new Random().nextInt();

        pojo.intField = newIntFieldValue;
        assertEquals(newIntFieldValue, reflectedObject.getValueOfField("intField"));
    }
}
