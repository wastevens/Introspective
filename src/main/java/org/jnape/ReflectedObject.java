package org.jnape;

import org.jnape.dynamiccollection.lambda.Function;

import java.lang.reflect.Field;
import java.util.List;

import static org.jnape.dynamiccollection.DynamicCollectionFactory.list;

public class ReflectedObject<Subject> {

    private final Subject subject;
    private final Class<?> reflectedClass;

    public ReflectedObject(Subject subject) {
        this.subject = subject;
        this.reflectedClass = subject.getClass();
    }

    public boolean hasField(String fieldName) {
        for (Field field : getAllFields())
            if (field.getName().equals(fieldName))
                return true;

        return false;
    }

    @SuppressWarnings({"unchecked"})
    public <Value> Value getValueOfField(final String fieldName) {
        try {
            return (Value) getField(fieldName).get(subject);
        } catch (IllegalAccessException impossibleException) {
            throw new Error("Can't get here.");
        }
    }

    private Field getField(final String fieldName) {
        if (!hasField(fieldName))
            return null;

        Function<Field, Boolean> desiredField = new Function<Field, Boolean>() {
            public Boolean apply(Field field) {
                return field.getName().equals(fieldName);
            }
        };

        return list(getAllFields()).collect(desiredField).get(0);
    }

    private List<Field> getAllFields() {
        return list(getInheritedFields()).concat(getDeclaredFields());
    }

    private List<Field> getInheritedFields() {
        return makeAccessible(reflectedClass.getFields());
    }

    private List<Field> getDeclaredFields() {
        return makeAccessible(reflectedClass.getDeclaredFields());
    }

    private List<Field> makeAccessible(Field[] fields) {
        Function<Field, Field> intoAccessibleFields = new Function<Field, Field>() {
            public Field apply(Field field) {
                field.setAccessible(true);
                return field;
            }
        };

        return list(fields).transform(intoAccessibleFields);
    }
}
