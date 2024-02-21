package xhu.spike.fusion.encrypt.digit;

import com.github.javaparser.StaticJavaParser;
import xhu.spike.fusion.Action;
import xhu.spike.fusion.JavaLoader;

public class DigitEncryption extends Action {
    private final Algorithm algorithm;

    public DigitEncryption(JavaLoader javaLoader) {
        super(javaLoader);
        algorithm = random.pick(new A001(random));
    }

    @Override
    public void doAction() {
        String[] lines = loader.getRoot().toString().split(LINE_SEPARATOR);
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(handleLine(line)).append('\n');
        }
        if (found) {
            loader.refresh(sb.toString());
            loader.getClazz().addMember(StaticJavaParser.parseMethodDeclaration(algorithm.genMethod()));
        }
    }

    private boolean found = false;

    private String handleLine(String line) {
        if (line.contains("static ")) {
            return line;
        }
        StringBuilder newLine = new StringBuilder();
        int begin = 0;
        Section s = detectInteger(line, begin);
        while (s != null) {
            newLine.append(line, begin, s.start);
            String p = line.substring(s.start, s.end);
            int orig = Integer.parseInt(p);
            int encrypted = algorithm.encrypt(orig);
            newLine.append(algorithm.recode(encrypted));
            begin = s.end;
            s = detectInteger(line, begin + 1);
        }
        if (begin == 0) {
            return line;
        }
        found = true;
        newLine.append(line, begin, line.length());
        return newLine.toString();
    }

    public Section detectInteger(String orig, int from) {
        int start = -1;
        for (int i = from; i < orig.length(); i++) {
            char current = orig.charAt(i);
            if (Character.isDigit(current)) {
                if (start < 0) {
                    char previous = orig.charAt(i - 1);
                    if (previous == ' '
                            || previous == ','
                            || previous == '['
                            || previous == '{'
                            || previous == '(') {
                        start = i;
                    } else if (previous == '-' || previous == '+') {
                        start = i - 1;
                    }
                }
            } else {
                if (start > 0) {
                    if (current == '.') {
                        start = -1;
                    } else {
                        return new Section(start, i);
                    }
                }
            }
        }
        return null;
    }

    private static class Section {
        private final int start;
        private final int end;
        Section(int s, int e) {
            start = s;
            end = e;
        }
    }
}
