package org.jnape.introspective;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ReflectedMethod implements ReflectedAccessible<Method> {
    private final Method subject;

    public ReflectedMethod(Method subject) {
        this.subject = subject;
    }

    public Visibility getVisibility() {
        return Visibility.forMember(subject);
    }

    public Method getSubject() {
        return subject;
    }

    public Class<?>[] getParameterTypes() {
        return subject.getParameterTypes();
    }

    public Boolean hasAnnotation(Class<? extends Annotation> annotationClass) {
        return subject.isAnnotationPresent(annotationClass);
    }

    public <AnnotationType extends Annotation> AnnotationType getAnnotation(Class<AnnotationType> annotationTypeClass) {
        return subject.getAnnotation(annotationTypeClass);
    }

    public Annotation[][] getParameterAnnotations() {
        return subject.getParameterAnnotations();
    }
}
