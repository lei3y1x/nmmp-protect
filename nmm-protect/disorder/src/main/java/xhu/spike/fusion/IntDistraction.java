package xhu.spike.fusion;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;

import java.util.List;

public class IntDistraction extends ExpressionDistraction {
    public IntDistraction(JavaLoader javaLoader, int h) {
        super(javaLoader, h);
    }

    @Override
    public void doAction() {
        super.doAction();
    }

    @Override
    public int onStatement(int index, BlockStmt b, CallableDeclaration m) {
        b.addStatement(index++, StaticJavaParser.parseStatement(randomExpression()));
        if (b.getStatements().size() - 1 == index && b.getStatement(index).isExpressionStmt()) {
            b.addStatement(randomExpression());
            index++;
        }
        return index;
    }

    private String randomExpression() {
        List<String> fields = loader.getNewIntFields();
        int[] indexes = random.pickNumbers(fields.size(), 4);
        String p1 = String.format("(%s%s%s)%s%d",
                fields.get(indexes[1]),
                random.pick("+", "-"),
                fields.get(indexes[2]),
                random.pick("%", "/"),
                random.randomInt(JavaLoader.MIN_INT, JavaLoader.MAX_INT));
        String p2 = String.format("%s%s%s%d",
                random.pick("+", "-"),
                fields.get(indexes[3]),
                random.pick("%", "/"),
                random.randomInt(JavaLoader.MIN_INT, JavaLoader.MAX_INT));
        String p3 = String.format("%s%s.length()",
                random.pick("+", "-", "%", "/"),
                random.pick(loader.getNewStrFields()));
        if (random.randomBool()) {
            String p = p2;
            p2 = p3;
            p3 = p;
        }
        return String.format("%s=%s%s%s;", fields.get(indexes[0]), p1, random.randomBool() ? p2 : "", random.randomBool() ? p3 : "");
    }
}
