package org.jnape.introspective;

import org.jnape.introspective.ReflectedObject;
import org.junit.Before;
import org.junit.Test;
import testsupport.A;

import static junit.framework.Assert.*;

public class ReflectedObjectTest {

    private A a;
    private ReflectedObject<A> reflectedObject;

    @Before
    public void setUp() {
        a = new A("");
        reflectedObject = new ReflectedObject<A>(a);
    }

    @Test
    public void shouldKnowIfHasField() {
//        assertTrue(reflectedObject.hasField("intField"));
//        assertTrue(reflectedObject.hasField("stringField"));
    }

    @Test
    public void shouldKnowIfDoesNotHaveField() {
        assertFalse(reflectedObject.hasField("impossible field"));
    }

    @Test
    public void shouldGetValueOfField() {
//        assertEquals(a.intField, reflectedObject.getValueOfField("intField"));
//        assertEquals(a.stringField, reflectedObject.getValueOfField("stringField"));
//
//        int newIntFieldValue = new Random().nextInt();
//
//        a.intField = newIntFieldValue;
//        assertEquals(newIntFieldValue, reflectedObject.getValueOfField("intField"));
    }
}
