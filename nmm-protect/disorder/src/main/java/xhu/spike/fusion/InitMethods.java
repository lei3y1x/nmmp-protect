package xhu.spike.fusion;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.BlockStmt;

import java.util.Optional;

public class InitMethods extends Action {
    public static final String[] types = new String[] {"int", "String", "Context"};

    private final int count;
    public InitMethods(JavaLoader javaLoader, int c) {
        super(javaLoader);
        count = c;
    }

    @Override
    public void doAction() {

        loader.addImport("android.content.Context");
        for (int i = 0; i < count; i++) {
            MethodDeclaration method = loader.addMethod();
            int[] paramIndexes= random.pickNumbers(types.length, random.randomInt(0, types.length + 1));
            for (int index : paramIndexes) {
                method.addParameter(StaticJavaParser.parseType(types[index]), random.randomName(3));
            }
            String t = types[random.randomInt(0, types.length)];
            method.setType(StaticJavaParser.parseType(t));
            Optional<Parameter> op = method.getParameterByType(t);
            BlockStmt b = method.findFirst(BlockStmt.class).get();

            String v = null;
            if (op.isPresent()) {
                v = op.get().getNameAsString();
            }
            if (v == null || v.length() == 0) {
                v = genReturnValue(t);
            }

            b.addStatement(String.format("return %s;", v));
        }
    }
}
