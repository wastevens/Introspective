package org.jnape.introspective;

import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.introspective.exception.FieldDoesNotExistOnClassException;
import org.jnape.introspective.exception.MethodDoesNotExistOnClassException;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.jnape.dynamiccollection.DynamicCollectionFactory.list;

public class ReflectedClass {

    private final Class<?> underlyingClass;

    public ReflectedClass(Class<?> underlyingClass) {
        this.underlyingClass = underlyingClass;
    }

    public <Subject> ReflectedClass(Subject subjectSource) {
        underlyingClass = subjectSource.getClass();
    }

    public Class<?> getUnderlyingClass() {
        return underlyingClass;
    }

    private static final DeclaredAccessibleFetcher<Field, ReflectedField> DECLARED_FIELD_FETCHER = new DeclaredAccessibleFetcher<Field, ReflectedField>() {
        public List<ReflectedField> getAccessibles(ReflectedClass reflectedClass) {
            return reflectedClass.getDeclaredFields();
        }
    };

    private static final DeclaredAccessibleFetcher<Method, ReflectedMethod> DECLARED_METHOD_FETCHER = new DeclaredAccessibleFetcher<Method, ReflectedMethod>() {
        public List<ReflectedMethod> getAccessibles(ReflectedClass reflectedClass) {
            return reflectedClass.getDeclaredMethods();
        }
    };

    public List<ReflectedField> getAllFields() {
        return list(getDeclaredFields()).concat(getInheritedFields());
    }

    public List<ReflectedField> getDeclaredFields() {
        return list(this.getUnderlyingClass().getDeclaredFields()).transform(new Function<Field, ReflectedField>() {
            public ReflectedField apply(Field field) {
                return new ReflectedField(field);
            }
        });
    }

    public List<ReflectedField> getInheritedFields() {
        return getInheritedAccessibleObjects(DECLARED_FIELD_FETCHER);
    }

    public ReflectedField getField(String fieldName) throws FieldDoesNotExistOnClassException {
        for (ReflectedField reflectedField : getAllFields())
            if (reflectedField.getSubject().getName().equals(fieldName))
                return reflectedField;

        throw new FieldDoesNotExistOnClassException(fieldName, underlyingClass);
    }

    public boolean hasField(Field field) {
        try {
            return getField(field.getName()).getSubject().equals(field);
        } catch (FieldDoesNotExistOnClassException e) {
            return false;
        }
    }

    public ReflectedMethod getMethod(String methodName, Class<?>... parameters) {
        for (ReflectedMethod reflectedMethod : getAllMethods())
            if (reflectedMethod.getSubject().getName().equals(methodName))
                if (list(parameters).equals(list(reflectedMethod.getParameterTypes())))
                    return reflectedMethod;

        throw new MethodDoesNotExistOnClassException(methodName, underlyingClass);
    }

    public List<ReflectedMethod> getAllMethods() {
        return list(getDeclaredMethods()).concat(getInheritedMethods());
    }

    public List<ReflectedMethod> getDeclaredMethods() {
        return list(this.getUnderlyingClass().getDeclaredMethods()).transform(new Function<Method, ReflectedMethod>() {
            public ReflectedMethod apply(Method method) {
                return new ReflectedMethod(method);
            }
        });
    }

    public List<ReflectedMethod> getInheritedMethods() {
        return getInheritedAccessibleObjects(DECLARED_METHOD_FETCHER);
    }

    public Boolean hasMethod(String methodName, Class<?>... parameters) {
        for (ReflectedMethod reflectedMethod : getAllMethods())
            if (reflectedMethod.getSubject().getName().equals(methodName))
                if (list(parameters).equals(list(reflectedMethod.getParameterTypes())))
                    return true;

        return false;
    }

    public Boolean hasAnnotation(Class<? extends Annotation> annotationClass) {
        return underlyingClass.isAnnotationPresent(annotationClass);
    }

    private <Target extends AccessibleObject, ReflectedTarget extends ReflectedAccessible<Target>> List<ReflectedTarget>
    getInheritedAccessibleObjects(DeclaredAccessibleFetcher<Target, ReflectedTarget> fetcher) {
        List<ReflectedTarget> inheritedFields = new ArrayList<ReflectedTarget>();

        for (Class<?> superClass = underlyingClass.getSuperclass(); superClass != null; superClass = superClass.getSuperclass()) {
            ReflectedClass reflectedSuperClass = new ReflectedClass(superClass);
            inheritedFields.addAll(getInheritableAccessibleObjects(reflectedSuperClass, fetcher));
        }

        return inheritedFields;
    }


    private <Target extends AccessibleObject, ReflectedTarget extends ReflectedAccessible<Target>>
    List<ReflectedTarget> getInheritableAccessibleObjects(ReflectedClass reflectedClass, DeclaredAccessibleFetcher<Target, ReflectedTarget> fetcher) {
        List<ReflectedTarget> inheritables = new ArrayList<ReflectedTarget>();

        Package subjectPackage = underlyingClass.getPackage();
        Package reflectedClassPackage = reflectedClass.underlyingClass.getPackage();

        for (ReflectedTarget reflectedAccessible : fetcher.getAccessibles(reflectedClass)) {
            switch (reflectedAccessible.getVisibility()) {
                case PUBLIC:
                case PROTECTED: {
                    inheritables.add(reflectedAccessible);
                    break;
                }

                case PACKAGE_PRIVATE: {
                    if (subjectPackage.equals(reflectedClassPackage))
                        inheritables.add(reflectedAccessible);
                }
            }
        }
        return inheritables;
    }

    private static interface DeclaredAccessibleFetcher<Target extends AccessibleObject, ReflectedTarget extends ReflectedAccessible<Target>> {
        public List<ReflectedTarget> getAccessibles(ReflectedClass reflectedClass);
    }
}
