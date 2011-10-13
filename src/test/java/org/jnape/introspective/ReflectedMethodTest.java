package org.jnape.introspective;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Test;
import testsupport.pojo.Bar;
import testsupport.pojo.Foo;
import testsupport.pojo.Unused;

import java.lang.annotation.Annotation;

import static junit.framework.Assert.*;
import static org.jnape.dynamiccollection.DynamicCollectionFactory.list;
import static org.jnape.introspective.Visibility.*;
import static testsupport.fixture.MethodFixture.*;

public class ReflectedMethodTest {

    @Test
    public void shouldConstruct() {
        new ReflectedMethod(METHOD_WITHOUT_PARAMETERS);
    }

    @Test
    public void shouldGetSubjectAndParameters() {
        ReflectedMethod reflectedMethod = new ReflectedMethod(METHOD_WITHOUT_PARAMETERS);
        assertTrue(EqualsBuilder.reflectionEquals(reflectedMethod.getSubject(), METHOD_WITHOUT_PARAMETERS));
        assertTrue(EqualsBuilder.reflectionEquals(reflectedMethod.getParameterTypes(), NO_PARAMETERS));

        ReflectedMethod anotherReflectedMethod = new ReflectedMethod(METHOD_WITH_PARAMETERS);
        assertTrue(EqualsBuilder.reflectionEquals(anotherReflectedMethod.getSubject(), METHOD_WITH_PARAMETERS));
        assertTrue(EqualsBuilder.reflectionEquals(anotherReflectedMethod.getParameterTypes(), METHOD_WITH_PARAMETERS_PARAMETER_ARRAY));
    }

    @Test
    public void shouldKnowVisibility() {
        ReflectedMethod publicReflectedMethod = new ReflectedMethod(PUBLIC_METHOD);
        assertEquals(PUBLIC, publicReflectedMethod.getVisibility());

        ReflectedMethod protectedReflectedMethod = new ReflectedMethod(PROTECTED_METHOD);
        assertEquals(PROTECTED, protectedReflectedMethod.getVisibility());

        ReflectedMethod packagePrivateReflectedMethod = new ReflectedMethod(PACKAGE_PRIVATE_METHOD);
        assertEquals(PACKAGE_PRIVATE, packagePrivateReflectedMethod.getVisibility());

        ReflectedMethod privateReflectedMethod = new ReflectedMethod(PRIVATE_METHOD);
        assertEquals(PRIVATE, privateReflectedMethod.getVisibility());
    }

    @Test
    public void shouldKnowIfHasAnnotation() {
        ReflectedMethod reflectedMethod = new ReflectedMethod(METHOD_WITH_ANNOTATIONS);

        assertTrue(reflectedMethod.hasAnnotation(Foo.class));
        assertTrue(reflectedMethod.hasAnnotation(Bar.class));
        assertFalse(reflectedMethod.hasAnnotation(Unused.class));
    }

    @Test
    public void shouldGetAnnotation() {
        ReflectedMethod reflectedMethod = new ReflectedMethod(METHOD_WITH_ANNOTATIONS);

        Foo fooAnnotationOnMethod = reflectedMethod.getAnnotation(Foo.class);
        assertEquals(fooAnnotationOnMethod.value(), METHOD_FOO_ANNOTATION_VALUE);

        Bar barAnnotationOnMethod = reflectedMethod.getAnnotation(Bar.class);
        assertEquals(barAnnotationOnMethod.value(), METHOD_BAR_ANNOTATION_VALUE);
    }

    @Test
    public void shouldGetParameterAnnotation() {
        ReflectedMethod reflectedMethod = new ReflectedMethod(METHOD_WITH_ANNOTATIONS);
        Annotation[][] parameterAnnotations = reflectedMethod.getParameterAnnotations();

        Foo fooAnnotationOnParameter = (Foo) parameterAnnotations[0][0];
        assertEquals(fooAnnotationOnParameter.value(), PARAMETER_FOO_ANNOTATION_VALUE);

        Bar barAnnotationOnParameter = (Bar) parameterAnnotations[0][1];
        assertEquals(barAnnotationOnParameter.value(), PARAMETER_BAR_ANNOTATION_VALUE);

        assertEquals(0, parameterAnnotations[1].length);
    }

}
