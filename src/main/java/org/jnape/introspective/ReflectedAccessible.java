package org.jnape.introspective;

import java.lang.reflect.AccessibleObject;

public interface ReflectedAccessible<Target extends AccessibleObject> {

    public Target getSubject();

    public Visibility getVisibility();

}
