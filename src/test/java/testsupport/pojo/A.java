package testsupport.pojo;

@Foo(value = "Class Annotation")
@Bar(value = "Another Class Annotation")
public class A extends Letter {

    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private final String label;

    public A(String label) {
        this.label = label;
    }
}
