package testsupport.fixture;

import org.jnape.introspective.ReflectedField;
import testsupport.pojo.A;
import testsupport.pojo.Letter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.jnape.dynamiccollection.DynamicCollectionFactory.list;

public class FieldFixture {

    public static final String LABEL_FIELD_NAME = "label";

    public static final Field LABEL_FIELD = getFieldFromClass(LABEL_FIELD_NAME, A.class);
    public static final Field PUBLIC_LETTER_FIELD = getFieldFromClass("publicLetterField", Letter.class);
    public static final Field PROTECTED_LETTER_FIELD = getFieldFromClass("protectedLetterField", Letter.class);
    public static final Field PACKAGE_PRIVATE_LETTER_FIELD = getFieldFromClass("packagePrivateLetterField", Letter.class);
    public static final Field PRIVATE_LETTER_FIELD = getFieldFromClass("privateLetterField", Letter.class);

    public static final List<ReflectedField> A_DECLARED_FIELDS = list(
            new ReflectedField(LABEL_FIELD)
    );

    public static final List<ReflectedField> A_INHERITED_FIELDS = list(
            new ReflectedField(PUBLIC_LETTER_FIELD),
            new ReflectedField(PROTECTED_LETTER_FIELD),
            new ReflectedField(PACKAGE_PRIVATE_LETTER_FIELD)
    );

    public static final List<ReflectedField> A_ALL_FIELDS = list(A_DECLARED_FIELDS).concat(A_INHERITED_FIELDS);

    public static final String A1_LABEL = "a1";
    public static final String A2_LABEL = "a2";

    public static final A A1 = new A(A1_LABEL);
    public static final A A2 = new A(A2_LABEL);

    private static Field getFieldFromClass(String fieldName, Class<?> clazz) {
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
