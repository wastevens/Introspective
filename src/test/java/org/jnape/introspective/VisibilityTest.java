package org.jnape.introspective;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static testsupport.fixture.FieldFixture.*;
import static testsupport.fixture.MethodFixture.*;

public class VisibilityTest {

    @Test
    public void shouldGetVisibilityForMembers() {
        assertEquals(Visibility.PUBLIC, Visibility.forMember(PUBLIC_LETTER_FIELD));
        assertEquals(Visibility.PROTECTED, Visibility.forMember(PROTECTED_LETTER_FIELD));
        assertEquals(Visibility.PRIVATE, Visibility.forMember(PRIVATE_LETTER_FIELD));
        assertEquals(Visibility.PACKAGE_PRIVATE, Visibility.forMember(PACKAGE_PRIVATE_LETTER_FIELD));

        assertEquals(Visibility.PUBLIC, Visibility.forMember(PUBLIC_METHOD));
        assertEquals(Visibility.PROTECTED, Visibility.forMember(PROTECTED_METHOD));
        assertEquals(Visibility.PRIVATE, Visibility.forMember(PRIVATE_METHOD));
        assertEquals(Visibility.PACKAGE_PRIVATE, Visibility.forMember(PACKAGE_PRIVATE_METHOD));
    }
}
