package org.jnape.introspective;

import org.jnape.dynamiccollection.DynamicList;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.introspective.exception.FieldDoesNotExistOnObjectException;

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

    public List<ReflectedField> getPublicFields() {
        return asReflectedFields(reflectedClass.getFields());
    }

    public List<ReflectedField> getDeclaredFields() {
        return asReflectedFields(reflectedClass.getDeclaredFields());
    }

    public ReflectedField getField(final String fieldName) {
        Function<ReflectedField, Boolean> fieldWithFieldName = new Function<ReflectedField, Boolean>() {
            public Boolean apply(ReflectedField reflectedField) {
                return reflectedField.getFieldName().equals(fieldName);
            }
        };

        List<ReflectedField> field = allFields().collect(fieldWithFieldName);

        if (field.isEmpty())
            throw new FieldDoesNotExistOnObjectException(fieldName, subject);

        return field.get(0);
    }

    public boolean hasField(String fieldName) {
        try {
            getField(fieldName);
            return true;
        } catch (FieldDoesNotExistOnObjectException noField) {
            return false;
        }
    }

    public Object getValueOfField(String fieldName) {
        return getField(fieldName).getValueFrom(subject);
    }

    private List<ReflectedField> asReflectedFields(Field... fields) {
        Function<Field, ReflectedField> intoReflectedFields = new Function<Field, ReflectedField>() {
            public ReflectedField apply(Field field) {
                return new ReflectedField(field);
            }
        };
        return list(fields).transform(intoReflectedFields);
    }

    private DynamicList<ReflectedField> allFields() {
        return list(getPublicFields()).concat(getDeclaredFields()).unique();
    }
}
