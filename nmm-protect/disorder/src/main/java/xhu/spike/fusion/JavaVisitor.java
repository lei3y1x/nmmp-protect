package xhu.spike.fusion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaVisitor {
    private final File root;
    private final List<File> files;
    public JavaVisitor(File r) {
        root = r;
        files = new ArrayList<>();
        load(root);
    }

    private void load(File f) {
        if (f.isDirectory()) {
            File[] targets = f.listFiles();
            if (targets == null) {
                return;
            }
            for (File t : targets) {
                load(t);
            }
        } else if (f.getName().endsWith(".java")) {
            files.add(f);
        }
    }

    public File[] getJavaFiles() {
        File[] result = new File[files.size()];
        files.toArray(result);
        return result;
    }
}
