package testsupport;

import java.lang.reflect.Field;

public class FieldFixture {

    public static final String A1_LABEL = "a label";
    public static final String A2_LABEL = "another label";

    public static final A A1 = new A(A1_LABEL);
    public static final A A2 = new A(A2_LABEL);

    public static final Field A1_FIELD = getLabelField(A1);
    public static final Field A2_FIELD = getLabelField(A2);

    private static Field getLabelField(A a) {
        try {
            return a.getClass().getDeclaredField("label");
        } catch (Exception impossibleException) {
            throw new Error("Can't get here.");
        }
    }
}
