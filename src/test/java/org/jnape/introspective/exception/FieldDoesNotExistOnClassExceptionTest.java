package org.jnape.introspective.exception;

import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static testsupport.fixture.FieldFixture.LABEL_FIELD;

@SuppressWarnings({"ThrowableInstanceNeverThrown"})
public class FieldDoesNotExistOnClassExceptionTest {

    @Test
    public void shouldConstruct() {
        new FieldDoesNotExistOnClassException(LABEL_FIELD, Object.class);
        new FieldDoesNotExistOnClassException(LABEL_FIELD.getName(), Object.class);
    }

    @Test
    public void shouldGetMessage() {
        Field field = LABEL_FIELD;
        Class<String> clazz = String.class;

        FieldDoesNotExistOnClassException exception1 = new FieldDoesNotExistOnClassException(field, clazz);
        assertEquals("Field <label> does not exist on <java.lang.String>", exception1.getMessage());

        FieldDoesNotExistOnClassException exception2 = new FieldDoesNotExistOnClassException(field.getName(), clazz);
        assertEquals("Field <label> does not exist on <java.lang.String>", exception2.getMessage());
    }

    @Test
    @SuppressWarnings({"ConstantConditions"})
    public void shouldOccurAtRuntime() {
        FieldDoesNotExistOnClassException exception = new FieldDoesNotExistOnClassException(LABEL_FIELD, Object.class);
        assertTrue(exception instanceof RuntimeException);
    }

}
