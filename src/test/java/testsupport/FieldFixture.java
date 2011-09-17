package testsupport;

import java.lang.reflect.Field;

public class FieldFixture {

    public static final String A1_LABEL = "a label";
    public static final String A2_LABEL = "another label";

    public static final A A1 = new A(A1_LABEL);
    public static final A A2 = new A(A2_LABEL);

    public static final Field A1_FIELD = getFieldFromObject("label", A1);
    public static final Field A2_FIELD = getFieldFromObject("label", A2);

    public static Field getFieldFromObject(String fieldName, Object object) {
        Class<?> clazz = object.getClass();

        try {
            return clazz.getDeclaredField(fieldName);
        } catch (Exception notDeclared) {
            try {
                return clazz.getField(fieldName);
            } catch (Exception notInherited) {
                return null;
            }
        }
    }
}
