package org.jnape.introspective.exception;

import java.lang.reflect.Field;

public class FieldDoesNotExistOnClassException extends RuntimeException {

    public FieldDoesNotExistOnClassException(String fieldName, Class<?> clazz) {
        super("Field <" + fieldName + "> does not exist on <" + clazz.getCanonicalName() + ">");
    }

    public FieldDoesNotExistOnClassException(Field field, Class<?> clazz) {
        this(field.getName(), clazz);
    }
}
