package xhu.spike.fusion;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* TODO:
    1.inner class not supported
    2.do nothing for interface
    3.static code not supported
    4.use int, string params
    5.invoke orig methods
    6.throw exceptions
    7.do not add code in while or for block
    8.save context to field
    9.StringBuilder,ArrayList,HashMap,Json,File Distraction
    10,Intent,Message,Preference Distraction
    11.possible if block
*/
public class JavaLoader {
    public static final int MIN_STR_LEN = 5;
    public static final int MAX_STR_LEN = 10;
    public static final int MIN_INT = 1000;
    public static final int MAX_INT = 30000;

    private static final int NAME_LEN = 6;

    private final File origFile;
    private CompilationUnit root;
    private ClassOrInterfaceDeclaration clazz;
    private List<CallableDeclaration> methods;
    // cached data
    private List<String> imports;
    private final List<String> newStrFields = new ArrayList<>();
    private final List<String> newIntFields = new ArrayList<>();
    private final List<MethodDeclaration> newMethods = new ArrayList<>();
    private final RandomEngine random;

    public JavaLoader(File file) throws Exception {
        origFile = file;
        random = RandomEngine.getIns();
        refresh(Tools.readAsString(file));
    }

    private void reset() {
        root = null;
        clazz = null;
        methods = null;
        imports = null;
    }

    public boolean refresh(String content) {
        root = StaticJavaParser.parse(content);
        Optional<ClassOrInterfaceDeclaration> target = root.findFirst(ClassOrInterfaceDeclaration.class);
        if (!target.isPresent()) {
            reset();
            return false;
        }
        clazz = target.get();
        if (clazz.isInterface()) {
            reset();
            return false;
        }
        methods = clazz.findAll(CallableDeclaration.class);
        imports = new ArrayList<>();
        root.getImports().forEach(imp -> imports.add(imp.getName().asString()));
        return true;
    }

    public String getContent() {
        return root.toString();
    }

    public CompilationUnit getRoot() {
        return root;
    }

    public ClassOrInterfaceDeclaration getClazz() {
        return clazz;
    }

    public List<CallableDeclaration> getMethods() {
        return methods;
    }

    public void dump() throws IOException {
        Tools.writeAsString(origFile, getContent());
    }

    public void addImport(String name) {
        if (imports.contains(name)) {
            return;
        }
        root.addImport(name);
        imports.add(name);
    }

    public String addStrField() {
        // TODO: mock meaningful stings?
        String name = random.randomName(NAME_LEN);
        String value = random.randomCodes(random.randomInt(MIN_STR_LEN, MAX_STR_LEN));
        clazz.addFieldWithInitializer(String.class, name,
                StaticJavaParser.parseExpression("\"" + value + "\""), Modifier.Keyword.PRIVATE);
        newStrFields.add(name);
        return name;
    }

    public List<String> getNewStrFields() {
        return newStrFields;
    }

    public String pickStrField() {
        return newStrFields.get(random.randomInt(0, newStrFields.size()));
    }

    public String addIntField() {
        String name = random.randomName(NAME_LEN);
        int value = random.randomInt(MIN_INT, MAX_INT);
        clazz.addFieldWithInitializer(int.class, name,
                StaticJavaParser.parseExpression(String.valueOf(value)), Modifier.Keyword.PRIVATE);
        newIntFields.add(name);
        return name;
    }

    public List<String> getNewIntFields() {
        return newIntFields;
    }

    public String pickIntField() {
        return newIntFields.get(random.randomInt(0, newIntFields.size()));
    }

    public MethodDeclaration addMethod() {
        String name = random.randomName(NAME_LEN);
        MethodDeclaration method = clazz.addMethod(name, Modifier.Keyword.PRIVATE);
        newMethods.add(method);
        methods.add(method);
        return method;
    }

    public List<MethodDeclaration> getNewMethods() {
        return newMethods;
    }

    public boolean isNewMethod(CallableDeclaration m) {
        for (MethodDeclaration newMethod : newMethods) {
            if (newMethod.getNameAsString().equals(m.getNameAsString())) {
                return true;
            }
        }
        return false;
    }
}
