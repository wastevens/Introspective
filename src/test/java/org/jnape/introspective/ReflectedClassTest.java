package org.jnape.introspective;

import org.jnape.introspective.exception.FieldDoesNotExistOnClassException;
import org.junit.Test;
import testsupport.pojo.A;
import testsupport.pojo.Letter;

import java.util.ArrayList;

import static junit.framework.Assert.*;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;
import static testsupport.fixture.FieldFixture.*;

public class ReflectedClassTest {

    @Test
    public void shouldConstruct() {
        new ReflectedClass(String.class);
    }

    @Test
    public void shouldGetSubject() {
        Class<String> subject1 = String.class;
        ReflectedClass reflectedClass1 = new ReflectedClass(subject1);
        assertEquals(subject1, reflectedClass1.getSubject());

        Class<Number> subject2 = Number.class;
        ReflectedClass reflectedClass2 = new ReflectedClass(subject2);
        assertEquals(subject2, reflectedClass2.getSubject());
    }

    @Test
    public void shouldGetDeclaredFields() {
        ReflectedClass reflectedClass = new ReflectedClass(A.class);
        assertReflectionEquals(A_DECLARED_FIELDS, reflectedClass.getDeclaredFields());
    }

    @Test
    public void shouldGetInheritedFields() {
        ReflectedClass reflectedClass1 = new ReflectedClass(A.class);
        assertReflectionEquals(A_INHERITED_FIELDS, reflectedClass1.getInheritedFields());

        ReflectedClass reflectedClass2 = new ReflectedClass(Object.class);
        assertReflectionEquals(new ArrayList<Object>(), reflectedClass2.getInheritedFields());
    }

    @Test
    public void shouldGetAllFields() {
        ReflectedClass reflectedClass = new ReflectedClass(A.class);
        assertReflectionEquals(A_ALL_FIELDS, reflectedClass.getAllFields());
    }

    @Test
    public void shouldGetField() {
        ReflectedClass reflectedA = new ReflectedClass(A.class);
        assertReflectionEquals(new ReflectedField(LABEL_FIELD), reflectedA.getField("label"));
        assertReflectionEquals(new ReflectedField(PUBLIC_LETTER_FIELD), reflectedA.getField("publicLetterField"));
        assertReflectionEquals(new ReflectedField(PROTECTED_LETTER_FIELD), reflectedA.getField("protectedLetterField"));
        assertReflectionEquals(new ReflectedField(PACKAGE_PRIVATE_LETTER_FIELD), reflectedA.getField("packagePrivateLetterField"));

        ReflectedClass reflectedLetter = new ReflectedClass(Letter.class);
        assertReflectionEquals(new ReflectedField(PUBLIC_LETTER_FIELD), reflectedLetter.getField("publicLetterField"));
        assertReflectionEquals(new ReflectedField(PROTECTED_LETTER_FIELD), reflectedLetter.getField("protectedLetterField"));
        assertReflectionEquals(new ReflectedField(PACKAGE_PRIVATE_LETTER_FIELD), reflectedLetter.getField("packagePrivateLetterField"));
        assertReflectionEquals(new ReflectedField(PRIVATE_LETTER_FIELD), reflectedLetter.getField("privateLetterField"));
    }

    @Test(expected = FieldDoesNotExistOnClassException.class)
    public void shouldThrowExceptionWhenGetInvalidField() {
        new ReflectedClass(A.class).getField("privateLetterField");
    }

    @Test
    public void shouldKnowIfHasField() {
        ReflectedClass reflectedA = new ReflectedClass(A.class);
        assertTrue(reflectedA.hasField(LABEL_FIELD));
        assertTrue(reflectedA.hasField(PUBLIC_LETTER_FIELD));
        assertTrue(reflectedA.hasField(PROTECTED_LETTER_FIELD));
        assertTrue(reflectedA.hasField(PACKAGE_PRIVATE_LETTER_FIELD));
        assertFalse(reflectedA.hasField(PRIVATE_LETTER_FIELD));

        ReflectedClass reflectedLetter = new ReflectedClass(Letter.class);
        assertFalse(reflectedLetter.hasField(LABEL_FIELD));
        assertTrue(reflectedLetter.hasField(PUBLIC_LETTER_FIELD));
        assertTrue(reflectedLetter.hasField(PROTECTED_LETTER_FIELD));
        assertTrue(reflectedLetter.hasField(PACKAGE_PRIVATE_LETTER_FIELD));
        assertTrue(reflectedLetter.hasField(PRIVATE_LETTER_FIELD));
    }
}
