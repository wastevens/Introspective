package org.jnape.introspective.exception;

import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static testsupport.FieldFixture.A1_FIELD;
import static testsupport.FieldFixture.A2_FIELD;

@SuppressWarnings({"ThrowableInstanceNeverThrown"})
public class FieldDoesNotExistOnObjectExceptionTest {

    @Test
    public void shouldConstruct() {
        new FieldDoesNotExistOnObjectException(A1_FIELD, new Object());
    }

    @Test
    public void shouldGetMessage() {
        Field field = A1_FIELD;
        Object object = "a string";

        FieldDoesNotExistOnObjectException exception = new FieldDoesNotExistOnObjectException(field, object);
        assertEquals("Field <label> does not exist on <java.lang.String>", exception.getMessage());
    }

    @Test
    public void shouldGetMessageWhenGivenNullAsObject() {
        Field field = A2_FIELD;

        FieldDoesNotExistOnObjectException exception = new FieldDoesNotExistOnObjectException(field, null);
        assertEquals("Field <label> does not exist on <null>", exception.getMessage());
    }

    @Test
    @SuppressWarnings({"ConstantConditions"})
    public void shouldOccurAtRuntime() {
        FieldDoesNotExistOnObjectException exception = new FieldDoesNotExistOnObjectException(A1_FIELD, "");
        assertTrue(exception instanceof RuntimeException);
    }

}
