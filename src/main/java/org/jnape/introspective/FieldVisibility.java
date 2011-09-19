package org.jnape.introspective;

import java.lang.reflect.Field;

import static java.lang.reflect.Modifier.*;

public enum FieldVisibility {
    PUBLIC,
    PROTECTED,
    PRIVATE,
    PACKAGE_PRIVATE;

    public static FieldVisibility forField(Field field) {
        int fieldVisibilityModifier = field.getModifiers();

        if (isPublic(fieldVisibilityModifier))
            return PUBLIC;

        else if (isProtected(fieldVisibilityModifier))
            return PROTECTED;

        else if (isPrivate(fieldVisibilityModifier))
            return PRIVATE;

        else
            return PACKAGE_PRIVATE;
    }
}
