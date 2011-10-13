package org.jnape.introspective;

import org.jnape.introspective.exception.FieldDoesNotExistOnClassException;
import org.jnape.introspective.exception.MethodDoesNotExistOnClassException;
import org.junit.Test;
import testsupport.pojo.*;

import java.util.ArrayList;

import static junit.framework.Assert.*;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;
import static testsupport.fixture.FieldFixture.*;
import static testsupport.fixture.MethodFixture.*;

public class ReflectedClassTest {

    @Test
    public void shouldConstruct() {
        new ReflectedClass(String.class);
        new ReflectedClass("a string");
    }

    @Test
    public void shouldGetSubject() {
        Class<String> subject1 = String.class;
        ReflectedClass reflectedClass1 = new ReflectedClass("Some string");
        assertEquals(subject1, reflectedClass1.getUnderlyingClass());

        Class<Number> subject2 = Number.class;
        ReflectedClass reflectedClass2 = new ReflectedClass(subject2);
        assertEquals(subject2, reflectedClass2.getUnderlyingClass());
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

    @Test
    public void shouldKnowIfHasAnnotation() {
        ReflectedClass reflectedA = new ReflectedClass(A.class);

        assertTrue(reflectedA.hasAnnotation(Foo.class));
        assertTrue(reflectedA.hasAnnotation(Bar.class));
        assertFalse(reflectedA.hasAnnotation(Unused.class));
    }

    @Test
    public void shouldKnowIfHasMethod() {
        ReflectedClass reflectedA = new ReflectedClass(A.class);

        assertTrue(reflectedA.hasMethod(METHOD_WITHOUT_PARAMETERS_NAME));
        assertTrue(reflectedA.hasMethod(METHOD_WITH_PARAMETERS_NAME, METHOD_WITH_PARAMETERS_PARAMETER_ARRAY));
        assertFalse(reflectedA.hasMethod(METHOD_WITH_PARAMETERS_NAME));
        assertFalse(reflectedA.hasMethod(METHOD_WITHOUT_PARAMETERS_NAME, METHOD_WITH_PARAMETERS_PARAMETER_ARRAY));

        assertTrue(reflectedA.hasMethod(PUBLIC_METHOD_NAME));
        assertTrue(reflectedA.hasMethod(PROTECTED_METHOD_NAME));
        assertTrue(reflectedA.hasMethod(PACKAGE_PRIVATE_METHOD_NAME));
        assertFalse(reflectedA.hasMethod(PRIVATE_METHOD_NAME));

        ReflectedClass reflectedLetter = new ReflectedClass(Letter.class);
        assertTrue(reflectedLetter.hasMethod(PUBLIC_METHOD_NAME));
        assertTrue(reflectedLetter.hasMethod(PROTECTED_METHOD_NAME));
        assertTrue(reflectedLetter.hasMethod(PACKAGE_PRIVATE_METHOD_NAME));
        assertTrue(reflectedLetter.hasMethod(PRIVATE_METHOD_NAME));
    }

    @Test
    public void shouldGetDeclaredMethods() {
        ReflectedClass reflectedClass = new ReflectedClass(A.class);
        assertReflectionEquals(A_DECLARED_METHODS, reflectedClass.getDeclaredMethods());
    }

    @Test
    public void shouldGetInheritedMethods() {
        ReflectedClass reflectedClass1 = new ReflectedClass(A.class);
        assertReflectionEquals(A_INHERITED_METHODS, reflectedClass1.getInheritedMethods());

        ReflectedClass reflectedClass2 = new ReflectedClass(Object.class);
        assertReflectionEquals(new ArrayList<ReflectedMethod>(), reflectedClass2.getInheritedMethods());
    }

    @Test
    public void shouldGetAllMethods() {
        ReflectedClass reflectedClass = new ReflectedClass(A.class);
        assertReflectionEquals(A_ALL_METHODS, reflectedClass.getAllMethods());
    }

    @Test
    public void shouldGetGivenMethod() {
        ReflectedClass reflectedClass = new ReflectedClass(A.class);
        assertReflectionEquals(new ReflectedMethod(METHOD_WITHOUT_PARAMETERS), reflectedClass.getMethod(METHOD_WITHOUT_PARAMETERS_NAME));
        assertReflectionEquals(new ReflectedMethod(METHOD_WITH_PARAMETERS), reflectedClass.getMethod(METHOD_WITH_PARAMETERS_NAME, METHOD_WITH_PARAMETERS_PARAMETER_ARRAY));

        ReflectedClass reflectedLetter = new ReflectedClass(Letter.class);
        assertReflectionEquals(new ReflectedMethod(PUBLIC_METHOD), reflectedLetter.getMethod(PUBLIC_METHOD_NAME));
        assertReflectionEquals(new ReflectedMethod(PROTECTED_METHOD), reflectedLetter.getMethod(PROTECTED_METHOD_NAME));
        assertReflectionEquals(new ReflectedMethod(PACKAGE_PRIVATE_METHOD), reflectedLetter.getMethod(PACKAGE_PRIVATE_METHOD_NAME));
        assertReflectionEquals(new ReflectedMethod(PRIVATE_METHOD), reflectedLetter.getMethod(PRIVATE_METHOD_NAME));
    }

    @Test(expected = MethodDoesNotExistOnClassException.class)
    public void shouldThrowExceptionWhenGetInvalidMethod() {
        new ReflectedClass(A.class).getMethod(PRIVATE_METHOD_NAME);
    }

}
