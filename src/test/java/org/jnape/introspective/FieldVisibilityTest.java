package org.jnape.introspective;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static testsupport.FieldFixture.*;

public class FieldVisibilityTest {

    @Test
    public void shouldGetVisibilityForField() {
        assertEquals(FieldVisibility.PUBLIC, FieldVisibility.forField(PUBLIC_FIELD));
        assertEquals(FieldVisibility.PROTECTED, FieldVisibility.forField(PROTECTED_FIELD));
        assertEquals(FieldVisibility.PRIVATE, FieldVisibility.forField(PRIVATE_FIELD));
        assertEquals(FieldVisibility.PACKAGE_PRIVATE, FieldVisibility.forField(PACKAGE_PRIVATE_FIELD));
    }
}
