package xhu.spike.fusion;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;

import java.util.List;

public class StrDistraction extends ExpressionDistraction {
    public StrDistraction(JavaLoader javaLoader, int h) {
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
        List<String> fields = loader.getNewStrFields();
        int[] indexes = random.pickNumbers(fields.size(), 3);
        return String.format("%s=(%s+%s).substring(%s%s.length());",
                fields.get(indexes[0]), fields.get(indexes[1]), fields.get(indexes[2]),
                random.randomBool() ? "0," : "", fields.get(indexes[2]));
    }
}
