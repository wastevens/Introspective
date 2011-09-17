package org.jnape.introspective.exception;

import java.lang.reflect.Field;

public class FieldDoesNotExistOnObjectException extends RuntimeException {

    public FieldDoesNotExistOnObjectException(String fieldName, Object object) {
        super("Field <" + fieldName + "> does not exist on <" + getClassName(object) + ">");
    }

    public FieldDoesNotExistOnObjectException(Field field, Object object) {
        this(field.getName(), object);
    }

    private static String getClassName(Object object) {
        if (object == null)
            return "null";

        return object.getClass().getCanonicalName();
    }
}
