import org.hyperskill.hstest.v6.stage.BaseStageTest;
import signature.MainKt;

public abstract class GeneratorTest<T> extends BaseStageTest<T> {
    public GeneratorTest() {
        super(MainKt.class);
    }
}
