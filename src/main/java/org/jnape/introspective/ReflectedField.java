package org.jnape.introspective;

import org.jnape.introspective.exception.FieldDoesNotExistOnObjectException;

import java.lang.reflect.Field;

public class ReflectedField {

    private final Field field;

    public ReflectedField(Field field) {
        field.setAccessible(true);
        this.field = field;
    }

    public boolean existsOn(Object object) {
        return field.equals(getField(object));
    }

    public Object getValueFrom(Object object) {
        try {
            return field.get(object);
        } catch (Exception e) {
            throw new FieldDoesNotExistOnObjectException(field, object);
        }
    }

    private Field getField(Object object) {
        Class<?> clazz = object.getClass();
        String fieldName = field.getName();

        try {
            return clazz.getDeclaredField(fieldName);
        } catch (Exception notDeclared) {
            try {
                return clazz.getField(fieldName);
            } catch (Exception notInherited) {
                return null;
            }
        }
    }
}
