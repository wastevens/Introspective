package testsupport.assertion;

import junit.framework.Assert;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;

public class ReflectionAssert {

    public static void assertReflectionEquals(Object expected, Object actual) {
        assertTrue(
                "Expected <" + expected + "> to be reflectively equal to <" + actual + ">, but it wasn't.",
                reflectionEquals(expected, actual)
        );
    }

    public static void assertReflectionEquals(List<?> expected, List<?> actual) {
        int expectedLength = expected.size();
        int actualLength = actual.size();

        Assert.assertEquals(
                "List sizes differ:",
                expectedLength,
                actualLength
        );

        for (int i = 0; i < expectedLength; i++)
            assertReflectionEquals(expected.get(i), actual.get(i));
    }
}
