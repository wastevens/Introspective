package testsupport.pojo;

@Foo(value = "Class Annotation")
@Bar(value = "Another Class Annotation")
public class A extends Letter {

    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private final String label;

    @Foo(value = "Field Annotation")
    @Bar(value = "Another Field Annotation")
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private final Integer annotatedField;

    public A(String label) {
        this.label = label;
        annotatedField = 0;
    }

    @Foo(value = "Method Annotation")
    @Bar(value = "Another Method Annotation")
    public void someMethod(@Foo("Arguement Annotation") @Bar("Another Arguement Annotation") String anArguement) {
    }
}
