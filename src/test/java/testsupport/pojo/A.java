package testsupport.pojo;

@Foo(value = "Class Annotation")
@Bar(value = "Another Class Annotation")
@SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
public class A extends Letter {

    private final String label;

    public A(String label) {
        this.label = label;
    }

    public String someMethodWithoutParameters() {
        return "";
    }

    public Integer someMethodWithParameters(String arg1, Object arg2, Letter arg3) {
        return 0;
    }

    @Foo("method annotation")
    @Bar("another method annotation")
    public void someMethodWithAnnotations(@Foo("parameter annotation") @Bar("another parameter annotation") Object argWithAnnotation, Object argWithoutAnnotation) {
    }
}
