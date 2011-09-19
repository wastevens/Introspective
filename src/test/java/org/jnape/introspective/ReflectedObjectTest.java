package org.jnape.introspective;

import org.jnape.introspective.exception.FieldDoesNotExistOnClassException;
import org.junit.Test;
import testsupport.pojo.A;

import static junit.framework.Assert.assertEquals;
import static testsupport.fixture.FieldFixture.*;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class ReflectedObjectTest {

    @Test
    public void shouldConstruct() {
        new ReflectedObject(A1);
    }

    @Test
    public void shouldGetSubject() {
        A subject1 = A1;
        ReflectedObject reflectedObject1 = new ReflectedObject(subject1);
        assertEquals(subject1, reflectedObject1.getSubject());

        A subject2 = A2;
        ReflectedObject reflectedObject2 = new ReflectedObject(subject2);
        assertEquals(subject2, reflectedObject2.getSubject());
    }

    @Test
    public void shouldGetReflectedClass() {
        String subject1 = "a string";
        ReflectedObject reflectedObject1 = new ReflectedObject(subject1);
        assertReflectionEquals(new ReflectedClass(String.class), reflectedObject1.getReflectedClass());

        Integer subject2 = 1;
        ReflectedObject reflectedObject2 = new ReflectedObject(subject2);
        assertReflectionEquals(new ReflectedClass(Integer.class), reflectedObject2.getReflectedClass());
    }

    @Test
    public void shouldGetValueOfField() {
        ReflectedObject reflectedObject1 = new ReflectedObject(A1);
        assertEquals(A1_LABEL, reflectedObject1.getValueOfField(LABEL_FIELD_NAME));

        ReflectedObject reflectedObject2 = new ReflectedObject(A2);
        assertEquals(A2_LABEL, reflectedObject2.getValueOfField(LABEL_FIELD_NAME));
    }

    @Test(expected = FieldDoesNotExistOnClassException.class)
    public void shouldThrowExceptionWhenGetValueOfInvalidField() {
        new ReflectedObject(A1).getValueOfField("this field can't possibly exist");
    }
}
