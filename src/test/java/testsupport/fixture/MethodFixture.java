package testsupport.fixture;

import org.jnape.introspective.ReflectedMethod;
import testsupport.pojo.A;
import testsupport.pojo.Letter;

import java.lang.reflect.Method;
import java.util.List;

import static org.jnape.dynamiccollection.DynamicCollectionFactory.list;

public class MethodFixture {
    public static final String METHOD_WITHOUT_PARAMETERS_NAME = "someMethodWithoutParameters";
    public static final Class<?>[] NO_PARAMETERS = {};
    public static final Method METHOD_WITHOUT_PARAMETERS = getMethodFromClass(METHOD_WITHOUT_PARAMETERS_NAME, A.class);

    public static final String METHOD_WITH_PARAMETERS_NAME = "someMethodWithParameters";
    public static final Class<?>[] METHOD_WITH_PARAMETERS_PARAMETER_ARRAY = {String.class, Object.class, Letter.class};
    public static final Method METHOD_WITH_PARAMETERS = getMethodFromClass(METHOD_WITH_PARAMETERS_NAME, A.class, METHOD_WITH_PARAMETERS_PARAMETER_ARRAY);

    public static final String PUBLIC_METHOD_NAME = "somePublicLetterMethod";
    public static final String PROTECTED_METHOD_NAME = "someProtectedLetterMethod";
    public static final String PACKAGE_PRIVATE_METHOD_NAME = "somePackagePrivateLetterMethod";
    public static final String PRIVATE_METHOD_NAME = "somePrivateLetterMethod";

    public static final Method PUBLIC_METHOD = getMethodFromClass(PUBLIC_METHOD_NAME, Letter.class);
    public static final Method PROTECTED_METHOD = getMethodFromClass(PROTECTED_METHOD_NAME, Letter.class);
    public static final Method PACKAGE_PRIVATE_METHOD = getMethodFromClass(PACKAGE_PRIVATE_METHOD_NAME, Letter.class);
    public static final Method PRIVATE_METHOD = getMethodFromClass(PRIVATE_METHOD_NAME, Letter.class);

    public static final String METHOD_WITH_ANNOTATIONS_NAME = "someMethodWithAnnotations";
    public static final Method METHOD_WITH_ANNOTATIONS = getMethodFromClass(METHOD_WITH_ANNOTATIONS_NAME, A.class, Object.class, Object.class);

    public static final String METHOD_FOO_ANNOTATION_VALUE = "method annotation";
    public static final String METHOD_BAR_ANNOTATION_VALUE = "another method annotation";

    public static final String PARAMETER_FOO_ANNOTATION_VALUE = "parameter annotation";
    public static final String PARAMETER_BAR_ANNOTATION_VALUE = "another parameter annotation";

    public static final List<ReflectedMethod> A_DECLARED_METHODS = list(
            new ReflectedMethod(METHOD_WITHOUT_PARAMETERS),
            new ReflectedMethod(METHOD_WITH_PARAMETERS),
            new ReflectedMethod(METHOD_WITH_ANNOTATIONS)
    );

    private static final List<ReflectedMethod> LETTER_METHODS = list(
            new ReflectedMethod(PUBLIC_METHOD),
            new ReflectedMethod(PROTECTED_METHOD),
            new ReflectedMethod(PACKAGE_PRIVATE_METHOD)
    );

    private static final List<ReflectedMethod> OBJECT_METHODS = list(
            new ReflectedMethod(getMethodFromClass("finalize", Object.class)),
            new ReflectedMethod(getMethodFromClass("wait", Object.class)),
            new ReflectedMethod(getMethodFromClass("wait", Object.class, long.class)),
            new ReflectedMethod(getMethodFromClass("wait", Object.class, long.class, int.class)),
            new ReflectedMethod(getMethodFromClass("equals", Object.class, Object.class)),
            new ReflectedMethod(getMethodFromClass("toString", Object.class)),
            new ReflectedMethod(getMethodFromClass("hashCode", Object.class)),
            new ReflectedMethod(getMethodFromClass("getClass", Object.class)),
            new ReflectedMethod(getMethodFromClass("clone", Object.class)),
            new ReflectedMethod(getMethodFromClass("notify", Object.class)),
            new ReflectedMethod(getMethodFromClass("notifyAll", Object.class))
    );

    public static final List<ReflectedMethod> A_INHERITED_METHODS = list(LETTER_METHODS).concat(OBJECT_METHODS);

    public static final List<ReflectedMethod> A_ALL_METHODS = list(A_DECLARED_METHODS).concat(A_INHERITED_METHODS);

    private static Method getMethodFromClass(String methodName, Class<?> clazz, Class<?>... parameters) {
        try {
            return clazz.getDeclaredMethod(methodName, parameters);
        } catch (Exception notDeclared) {
            try {
                return clazz.getMethod(methodName, parameters);
            } catch (Exception notInherited) {
                return null;
            }
        }
    }
}
