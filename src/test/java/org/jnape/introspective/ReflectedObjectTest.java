package org.jnape.introspective;

import org.jnape.introspective.exception.FieldDoesNotExistOnObjectException;
import org.junit.Test;
import testsupport.A;
import testsupport.ReflectedFieldAssert;

import java.util.List;

import static junit.framework.Assert.*;
import static org.jnape.dynamiccollection.DynamicCollectionFactory.list;
import static testsupport.FieldFixture.*;

public class ReflectedObjectTest {

    @Test
    public void shouldConstruct() {
        new ReflectedObject<A>(A1);
    }

    @Test
    public void shouldGetPublicFields() {
        ReflectedObject<A> reflectedObject = new ReflectedObject<A>(A1);

        List<ReflectedField> expected = list(new ReflectedField(getFieldFromObject("inheritedField", A1)));
        ReflectedFieldAssert.assertEquals(expected, reflectedObject.getPublicFields());
    }

    @Test
    public void shouldGetDeclaredFields() {
        ReflectedObject<A> reflectedObject = new ReflectedObject<A>(A1);

        List<ReflectedField> expected = list(new ReflectedField(getFieldFromObject("label", A1)));
        ReflectedFieldAssert.assertEquals(expected, reflectedObject.getDeclaredFields());
    }

    @Test
    public void shouldGetField() {
        ReflectedObject<A> reflectedObject = new ReflectedObject<A>(A1);
        String labelFieldName = "label";

        ReflectedField expected = new ReflectedField(getFieldFromObject(labelFieldName, A1));

        ReflectedFieldAssert.assertEquals(expected, reflectedObject.getField(labelFieldName));
    }

    @Test(expected = FieldDoesNotExistOnObjectException.class)
    public void shouldThrowExceptionIfGetInvalidField() {
        new ReflectedObject<A>(A1).getField("invalid field");
    }

    @Test
    public void shouldKnowIfHasField() {
        ReflectedObject<A> reflectedObject = new ReflectedObject<A>(A1);

        assertTrue(reflectedObject.hasField("label"));
        assertTrue(reflectedObject.hasField("inheritedField"));
        assertFalse(reflectedObject.hasField("not a field"));
    }

    @Test
    public void shouldGetValueOfField() {
        String fieldName = "label";

        ReflectedObject<A> reflectedObject1 = new ReflectedObject<A>(A1);
        assertEquals(A1_LABEL, reflectedObject1.getValueOfField(fieldName));

        ReflectedObject<A> reflectedObject2 = new ReflectedObject<A>(A2);
        assertEquals(A2_LABEL, reflectedObject2.getValueOfField(fieldName));
    }

    @Test(expected = FieldDoesNotExistOnObjectException.class)
    public void shouldThrowExceptionIfGetValueOfInvalidField() {
        new ReflectedObject<A>(A1).getValueOfField("invalid field");
    }
}
