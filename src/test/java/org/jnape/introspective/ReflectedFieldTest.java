package org.jnape.introspective;

import org.jnape.introspective.exception.FieldDoesNotExistOnObjectException;
import org.junit.Test;
import testsupport.FieldFixture;

import java.lang.reflect.Field;

import static junit.framework.Assert.*;
import static testsupport.FieldFixture.*;

public class ReflectedFieldTest {

    private static final Field LABEL = getFieldFromObject("label", A1);
    private static final Field INHERITED_FIELD = getFieldFromObject("inheritedField", A1);

    @Test
    public void shouldConstruct() {
        new ReflectedField(LABEL);
    }

    @Test
    public void shouldGetField() {
        ReflectedField reflectedField1 = new ReflectedField(LABEL);
        assertEquals(LABEL, reflectedField1.getField());

        ReflectedField reflectedField2 = new ReflectedField(INHERITED_FIELD);
        assertEquals(INHERITED_FIELD, reflectedField2.getField());
    }

    @Test
    public void shouldGetFieldName() {
        ReflectedField reflectedField1 = new ReflectedField(LABEL);
        assertEquals(LABEL.getName(), reflectedField1.getFieldName());

        ReflectedField reflectedField2 = new ReflectedField(INHERITED_FIELD);
        assertEquals(INHERITED_FIELD.getName(), reflectedField2.getFieldName());
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
