package org.jnape.introspective;

import org.jnape.introspective.exception.FieldDoesNotExistOnClassException;
import org.junit.Test;

import static junit.framework.Assert.*;
import static org.jnape.introspective.Visibility.*;
import static testsupport.fixture.FieldFixture.*;

public class ReflectedFieldTest {

    @Test
    public void shouldConstruct() {
        new ReflectedField(LABEL_FIELD);
    }

    @Test
    public void shouldGetSubject() {
        ReflectedField reflectedField1 = new ReflectedField(LABEL_FIELD);
        assertEquals(LABEL_FIELD, reflectedField1.getSubject());

        ReflectedField reflectedField2 = new ReflectedField(PUBLIC_LETTER_FIELD);
        assertEquals(PUBLIC_LETTER_FIELD, reflectedField2.getSubject());
    }

    @Test
    public void shouldKnowIfExistsOnObject() {
        ReflectedField reflectedField = new ReflectedField(LABEL_FIELD);

        assertTrue(reflectedField.existsOn(A1));
        assertTrue(reflectedField.existsOn(A2));
        assertFalse(reflectedField.existsOn(new Object()));
    }

    @Test
    public void shouldGetValueFromObject() {
        ReflectedField reflectedField = new ReflectedField(LABEL_FIELD);

        assertEquals(A1_LABEL, reflectedField.getValueFrom(A1));
        assertEquals(A2_LABEL, reflectedField.getValueFrom(A2));
    }

    @Test(expected = FieldDoesNotExistOnClassException.class)
    public void shouldThrowExceptionWhenGetValueFromInvalidObject() {
        ReflectedField reflectedField = new ReflectedField(LABEL_FIELD);
        reflectedField.getValueFrom("a string, which does not have a label field");
    }

    @Test
    public void shouldKnowVisibility() {
        ReflectedField publicReflectedField = new ReflectedField(PUBLIC_LETTER_FIELD);
        assertEquals(PUBLIC, publicReflectedField.getVisibility());

        ReflectedField protectedReflectedField = new ReflectedField(PROTECTED_LETTER_FIELD);
        assertEquals(PROTECTED, protectedReflectedField.getVisibility());

        ReflectedField privateReflectedField = new ReflectedField(PRIVATE_LETTER_FIELD);
        assertEquals(PRIVATE, privateReflectedField.getVisibility());

        ReflectedField packagePrivateReflectedField = new ReflectedField(PACKAGE_PRIVATE_LETTER_FIELD);
        assertEquals(PACKAGE_PRIVATE, packagePrivateReflectedField.getVisibility());
    }
}
