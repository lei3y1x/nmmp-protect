package xhu.spike.fusion;

public class InitFields extends Action {
    private final int count;
    public InitFields(JavaLoader javaLoader, int c) {
        super(javaLoader);
        count = c;
    }

    @Override
    public void doAction() {
        for (int i = 0; i < count; i++) {
            loader.addStrField();
            loader.addIntField();
        }
    }
}
