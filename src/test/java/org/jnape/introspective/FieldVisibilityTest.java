package org.jnape.introspective;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static testsupport.fixture.FieldFixture.*;

public class FieldVisibilityTest {

    @Test
    public void shouldGetVisibilityForField() {
        assertEquals(FieldVisibility.PUBLIC, FieldVisibility.forField(PUBLIC_LETTER_FIELD));
        assertEquals(FieldVisibility.PROTECTED, FieldVisibility.forField(PROTECTED_LETTER_FIELD));
        assertEquals(FieldVisibility.PRIVATE, FieldVisibility.forField(PRIVATE_LETTER_FIELD));
        assertEquals(FieldVisibility.PACKAGE_PRIVATE, FieldVisibility.forField(PACKAGE_PRIVATE_LETTER_FIELD));
    }
}
