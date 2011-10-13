package org.jnape.introspective;

import org.jnape.introspective.exception.FieldDoesNotExistOnClassException;

import java.lang.reflect.Field;

public class ReflectedField implements ReflectedAccessible<Field> {

    private final Field subject;

    public ReflectedField(Field subject) {
        subject.setAccessible(true);
        this.subject = subject;
    }

    public Field getSubject() {
        return subject;
    }

    public boolean existsOn(Object object) {
        Class<?> clazz = object.getClass();
        return new ReflectedClass(clazz).hasField(subject);
    }

    public Object getValueFrom(Object object) {
        try {
            return subject.get(object);
        } catch (Exception e) {
            throw new FieldDoesNotExistOnClassException(subject, object.getClass());
        }
    }

    public Visibility getVisibility() {
        return Visibility.forMember(subject);
    }
}
