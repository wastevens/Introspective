package org.jnape.introspective.exception;

import java.lang.reflect.Field;

public class FieldDoesNotExistOnObjectException extends RuntimeException {

    public FieldDoesNotExistOnObjectException(Field field, Object object) {
        super("Field <" + field.getName() + "> does not exist on <" + getClassName(object) + ">");
    }

    private static String getClassName(Object object) {
        if (object == null)
            return "null";

        return object.getClass().getCanonicalName();
    }
}
