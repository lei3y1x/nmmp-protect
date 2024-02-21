package xhu.spike.fusion;

import com.github.javaparser.StaticJavaParser;

public abstract class LineAction extends Action {
    private boolean needRefresh;

    public LineAction(JavaLoader javaLoader) {
        super(javaLoader);
    }

    public abstract String handleLine(String line);

    public void markRefresh() {
        needRefresh = true;
    }

    @Override
    public void doAction() {
        String[] lines = loader.getRoot().toString().split(LINE_SEPARATOR);
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(handleLine(line)).append('\n');
        }
        if (needRefresh) {
            loader.refresh(sb.toString());
        }
    }
}
