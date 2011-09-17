package org.jnape.introspective;

import org.jnape.introspective.exception.FieldDoesNotExistOnObjectException;
import org.junit.Test;
import testsupport.FieldFixture;

import static junit.framework.Assert.*;
import static testsupport.FieldFixture.*;

public class ReflectedFieldTest {

    @Test
    public void shouldConstruct() {
        new ReflectedField(A1_FIELD);
    }

    @Test
    public void shouldKnowIfExistsOnObject() {
        ReflectedField reflectedField = new ReflectedField(A1_FIELD);

        assertTrue(reflectedField.existsOn(A2));
        assertFalse(reflectedField.existsOn(new Object()));
    }

    @Test
    public void shouldGetValueFromObjectWithSameField() {
        ReflectedField reflectedField1 = new ReflectedField(A1_FIELD);
        assertEquals(FieldFixture.A1_LABEL, reflectedField1.getValueFrom(A1));

        ReflectedField reflectedField2 = new ReflectedField(A2_FIELD);
        assertEquals(A2_LABEL, reflectedField2.getValueFrom(A2));
    }

    @Test(expected = FieldDoesNotExistOnObjectException.class)
    public void shouldThrowExceptionWhenGetValueFromInvalidObject() {
        ReflectedField reflectedField = new ReflectedField(A1_FIELD);
        reflectedField.getValueFrom("a string, which does not have a label field");
    }
}
