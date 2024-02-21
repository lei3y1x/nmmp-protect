package xhu.spike.fusion;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.List;

public abstract class ExpressionDistraction extends Action {
    protected final int hit;
    public ExpressionDistraction(JavaLoader javaLoader,int h) {
        super(javaLoader);
        hit = h;
    }

    @Override
    public void doAction() {
        for (CallableDeclaration m : loader.getMethods()) {
            if (!checkMethod(m)) {
                continue;
            }
            List<BlockStmt> blockStmts = m.findAll(BlockStmt.class);
            for (BlockStmt b : blockStmts) {
                if (!checkBlock(b)) {
                    continue;
                }
                NodeList<Statement> list = b.getStatements();
                for (int i = 0; i < list.size(); i++) {
                    if (checkStatement(i, b, m)) {
                        i = onStatement(i, b, m);
                    }
                }
            }
        }
    }

    protected boolean checkMethod(CallableDeclaration m) {
        // TODO: support static methods
        return !m.isStatic();
    }

    protected boolean checkBlock(BlockStmt b) {
        return true;
    }

    protected boolean checkStatement(int index, BlockStmt b, CallableDeclaration m) {
        return random.hit(hit);
    }

    public abstract int onStatement(int index, BlockStmt b, CallableDeclaration m);

    /**
     * @param min (inclusive)
     * @param max (exclusive) must be larger than min
     * @param negative allow negative number or not
     * */
    protected String genImpossibleIntComparation(int min, int max, boolean negative, boolean equalsOnly) {
        String[] compareUp = new String[] {"==", ">=", ">"};
        int lengthUp = random.randomInt(max, max + 100);
        String[] compareDown = new String[] {"==", "<=", "<"};
        int lengthDown = negative ? random.randomInt(min - 100, min) : random.randomInt(1, min);
        boolean upOrDown = random.randomBool();
        String[] compare = upOrDown ? compareUp : compareDown;
        int length = upOrDown ? lengthUp : lengthDown;
        return (equalsOnly ? "==" : compare[random.randomInt(0, compare.length)]) + " " + length;
    }
}
