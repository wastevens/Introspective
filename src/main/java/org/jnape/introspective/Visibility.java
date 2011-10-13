package org.jnape.introspective;

import java.lang.reflect.Member;

import static java.lang.reflect.Modifier.*;

public enum Visibility {
    PUBLIC,
    PROTECTED,
    PRIVATE,
    PACKAGE_PRIVATE;

    public static Visibility forMember(Member member) {
        int modifiers = member.getModifiers();

        if (isPublic(modifiers))
            return PUBLIC;

        else if (isProtected(modifiers))
            return PROTECTED;

        else if (isPrivate(modifiers))
            return PRIVATE;

        else
            return PACKAGE_PRIVATE;
    }
}
