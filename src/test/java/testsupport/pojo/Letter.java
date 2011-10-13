package testsupport.pojo;

@SuppressWarnings({"UnusedDeclaration"})
public class Letter {

    public final int publicLetterField = 0;

    protected final int protectedLetterField = 0;

    final int packagePrivateLetterField = 0;

    private final int privateLetterField = 0;

    public final void somePublicLetterMethod() {
    }

    protected final void someProtectedLetterMethod() {
    }

    final void somePackagePrivateLetterMethod() {
    }

    private final void somePrivateLetterMethod() {
    }
}
