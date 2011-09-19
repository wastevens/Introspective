package org.jnape.introspective;

import org.jnape.introspective.exception.FieldDoesNotExistOnClassException;

public class ReflectedObject {

    private final Object subject;
    private final ReflectedClass reflectedClass;

    public ReflectedObject(Object subject) {
        this.subject = subject;
        this.reflectedClass = new ReflectedClass(subject.getClass());
    }

    public Object getSubject() {
        return subject;
    }

    public ReflectedClass getReflectedClass() {
        return reflectedClass;
    }

    public Object getValueOfField(String fieldName) throws FieldDoesNotExistOnClassException {
        return reflectedClass.getField(fieldName).getValueFrom(subject);
    }
}
