package xhu.spike.fusion;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;

public class LogDistraction extends ExpressionDistraction {

    public LogDistraction(JavaLoader javaLoader, int h) {
        super(javaLoader, h);
    }

    @Override
    public void doAction() {
        loader.addImport("android.util.Log");
        super.doAction();
    }

    @Override
    public int onStatement(int index, BlockStmt b, CallableDeclaration m) {
        b.addStatement(index++, StaticJavaParser.parseStatement(randomExpression(m)));
        if (b.getStatements().size() - 1 == index && b.getStatement(index).isExpressionStmt()) {
            b.addStatement(randomExpression(m));
            index++;
        }
        return index;
    }

    private String randomExpression(CallableDeclaration m) {
        int wordCount = random.randomInt(4, 8);
        StringBuilder msg = new StringBuilder();
        for (int i = 0 ; i < wordCount; i++) {
            if (msg.length() > 0) {
                msg.append(' ');
            }
            if (random.hit(20)) {
                msg.append(random.randomInt(1, 20)).append(' ');
            }
            msg.append(Data.words[random.randomInt(0, Data.words.length)]);
        }
        String tag;
        int paramCount = m.getParameters().size();
        switch (random.randomInt(0, 2 + (paramCount > 0 ? 2 : 0))) {
            case 0:
                tag = Data.words[random.randomInt(0, Data.words.length)];
                break;
            case 1:
                tag = m.getNameAsString();
                break;
            case 2:
                tag = m.getParameter(random.randomInt(0, paramCount)).getNameAsString();
                break;
            case 3:
                tag = m.getParameter(random.randomInt(0, paramCount)).getTypeAsString();
                break;
            default:
                tag = "default";
                break;
        }
        return String.format("Log.%s(\"%s\", \"%s\");",
                random.pick("i", "v", "w", "d", "d", "e", "e"),
                tag,
                msg.toString());
    }
}
