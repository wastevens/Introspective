package org.jnape.introspective;

import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.introspective.exception.FieldDoesNotExistOnClassException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.jnape.dynamiccollection.DynamicCollectionFactory.list;

public class ReflectedClass {

    private final Class<?> subject;

    public ReflectedClass(Class<?> subject) {
        this.subject = subject;
    }

    public Class<?> getSubject() {
        return subject;
    }

    public List<ReflectedField> getDeclaredFields() {
        return list(subject.getDeclaredFields()).transform(new Function<Field, ReflectedField>() {
            public ReflectedField apply(Field field) {
                return new ReflectedField(field);
            }
        });
    }

    public List<ReflectedField> getInheritedFields() {
        List<ReflectedField> inheritedFields = new ArrayList<ReflectedField>();

        for (Class<?> superClass = subject.getSuperclass(); superClass != null; superClass = superClass.getSuperclass()) {
            ReflectedClass reflectedSuperClass = new ReflectedClass(superClass);
            inheritedFields.addAll(getInheritableFields(reflectedSuperClass));
        }

        return inheritedFields;
    }

    public List<ReflectedField> getAllFields() {
        return list(getDeclaredFields()).concat(getInheritedFields());
    }

    public ReflectedField getField(String fieldName) throws FieldDoesNotExistOnClassException {
        for (ReflectedField reflectedField : getAllFields())
            if (reflectedField.getSubject().getName().equals(fieldName))
                return reflectedField;

        throw new FieldDoesNotExistOnClassException(fieldName, subject);
    }

    public boolean hasField(Field field) {
        try {
            return getField(field.getName()).getSubject().equals(field);
        } catch (FieldDoesNotExistOnClassException e) {
            return false;
        }
    }

    private List<ReflectedField> getInheritableFields(ReflectedClass reflectedClass) {
        List<ReflectedField> inheritableFields = new ArrayList<ReflectedField>();

        Package subjectPackage = subject.getPackage();
        Package reflectedClassPackage = reflectedClass.subject.getPackage();

        for (ReflectedField reflectedField : reflectedClass.getDeclaredFields()) {
            switch (reflectedField.getVisibility()) {
                case PUBLIC:
                case PROTECTED: {
                    inheritableFields.add(reflectedField);
                    break;
                }

                case PACKAGE_PRIVATE: {
                    if (subjectPackage.equals(reflectedClassPackage))
                        inheritableFields.add(reflectedField);
                }
            }
        }

        return inheritableFields;
    }
}
