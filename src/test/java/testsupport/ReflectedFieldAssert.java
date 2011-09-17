package testsupport;

import junit.framework.Assert;
import org.jnape.introspective.ReflectedField;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;

public class ReflectedFieldAssert {

    public static void assertEquals(ReflectedField expected, ReflectedField actual) {
        assertTrue(
                "Expected <" + expected + "> to be reflectively equal to <" + actual + ">, but it wasn't.",
                reflectionEquals(expected, actual)
        );
    }

    public static void assertEquals(List<ReflectedField> expected, List<ReflectedField> actual) {
        int expectedLength = expected.size();
        int actualLength = actual.size();

        Assert.assertEquals(
                "Expected length to be <" + expectedLength + ">, but was <" + actualLength + ">",
                expectedLength,
                actualLength
        );

        for (int i = 0; i < expectedLength; i++)
            assertEquals(expected.get(i), actual.get(i));
    }
}
