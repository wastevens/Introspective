package org.jnape.introspective.exception;

import java.lang.reflect.Field;

public class MethodDoesNotExistOnClassException extends RuntimeException {

    public MethodDoesNotExistOnClassException(String methodName, Class<?> clazz) {
        super("Method <" + methodName + "> does not exist on <" + clazz.getCanonicalName() + ">");
    }

    public MethodDoesNotExistOnClassException(Field field, Class<?> clazz) {
        this(field.getName(), clazz);
    }
}
