package xhu.spike.fusion;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.BlockStmt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReturnDistraction extends ExpressionDistraction {
    // TODO: currently max sdk version is 32 (Android 12L BETA)
    private static final int MAX_SDK_INT = 33;
    // TODO: no need to support sdk version less than 16
    private static final int MIN_SDK_INT = 16;
    private static final String[] AndroidStrings = new String[] {
            "Build.MODEL", "Build.BRAND", "Build.MANUFACTURER", "Build.PRODUCT",
            "BatteryManager.EXTRA_HEALTH", "BatteryManager.EXTRA_SCALE", "BatteryManager.EXTRA_LEVEL", "BatteryManager.EXTRA_STATUS",
            "Environment.DIRECTORY_ALARMS", "Environment.DIRECTORY_DCIM", "Environment.DIRECTORY_PICTURES", "Environment.DIRECTORY_DOWNLOADS",
            "Notification.EXTRA_TEXT", "Notification.EXTRA_SUB_TEXT", "Notification.EXTRA_TITLE",
            "Activity.ACTIVITY_SERVICE", "Activity.APPWIDGET_SERVICE", "Activity.DISPLAY_SERVICE", "Activity.DOWNLOAD_SERVICE",
    };

    private int[] strIndexes;
    private int strIndex;

    public ReturnDistraction(JavaLoader javaLoader, int h) {
        super(javaLoader, h);
    }

    @Override
    public void doAction() {
        // according to AndroidStrings
        loader.addImport("android.os.Build");
        loader.addImport("android.os.BatteryManager");
        loader.addImport("android.os.Environment");
        loader.addImport("android.app.Notification");
        loader.addImport("android.app.Activity");
        super.doAction();
    }

    private void refreshStrIndexes() {
        strIndexes = random.pickNumbers(loader.getNewStrFields().size(), loader.getNewStrFields().size());
        strIndex = 0;
    }

    @Override
    protected boolean checkMethod(CallableDeclaration m) {
        refreshStrIndexes();
        return super.checkMethod(m);
    }

    @Override
    public int onStatement(int index, BlockStmt b, CallableDeclaration m) {
        String type = "void";
        if (m.isMethodDeclaration()) {
            type = ((MethodDeclaration)m).getType().asString();
        }
        b.addStatement(index++, StaticJavaParser.parseStatement(genReturn(type, m)));
        if (b.getStatements().size() - 1 == index && b.getStatement(index).isExpressionStmt()) {
            b.addStatement(genReturn(type, m));
            index++;
        }
        return index;
    }

    private String genReturn(String type, CallableDeclaration m) {
        return String.format("if(%s){%s%s}",
                genCondition(m),
                genInvokeNewMethod(),
                String.format("return %s;", genReturnValue(type)));
    }

    private String genInvokeNewMethod() {
        // TODO: use parent method parameter
        MethodDeclaration method = random.pick(loader.getNewMethods());
        StringBuilder sb = new StringBuilder();
        method.getParameters().forEach(parameter -> {
            String t = parameter.getType().asString();
            sb.append(genReturnValue(t)).append(',');
        });
        String params = sb.toString();
        if (params.length() > 0) {
            params =  params.substring(0, params.length() - 1);
        } else {
            params = "";
        }
        String t = method.getTypeAsString();
        String value = "";
        if ("int".equals(t)) {
            value = random.pick(loader.getNewIntFields())+"=";
        } else if ("String".equals(t)) {
            value = random.pick(loader.getNewStrFields()) + "=";
        }
        return value + method.getNameAsString() + "(" + params + ");";
    }

    private String genCondition(CallableDeclaration m) {
        // TODO: string.contains, string.indexOf
        // TODO: build name
        // TODO: xxx + (100000, 200000) == yyy - (100000, 200000)
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new SdkVersion());
        conditions.add(new StringLength());
        conditions.add(new StringEquals());
        conditions.add(new Calendar());
        conditions.add(new SystemTime());

        if (!loader.isNewMethod(m)) {
            Optional<Parameter> p = m.getParameterByType("Context");
            p.ifPresent(param -> {
                String name = param.getNameAsString();
                conditions.add(new PackageName(name));
                conditions.add(new ContextApiNull(name));
                conditions.add(new ApplicationInfo(name));
            });
        }

        return random.pick(conditions).genCondition();
    }

    private String popStringField() {
        if (strIndex >= strIndexes.length) {
            return loader.addStrField();
        }
        return loader.getNewStrFields().get(strIndexes[strIndex++]);
    }

    private String pickIntField() {
        return loader.getNewIntFields().get(random.randomInt(0, loader.getNewIntFields().size()));
    }

    private String pickAndroidString() {
        return AndroidStrings[random.randomInt(0, AndroidStrings.length)];
    }

    private static abstract class Condition {
        abstract String genCondition();
    }

    private class SystemTime extends Condition {
        @Override
        String genCondition() {
            if (random.randomBool()) {
                return "System.currentTimeMillis() == "
                        + (random.randomBool() ? random.randomInt(1000000, 999999999) : pickIntField());
            } else {
                int base = random.randomInt(1000000, 9999999);
                return String.format("System.currentTimeMillis() %% %d == %s", base,
                        random.randomBool() ? String.valueOf(base - random.randomInt(10, 999000)) : pickIntField());
            }
        }
    }

    private String genStringEqualsExpr(String v1, String v2) {
        String compare = random.randomBool() ? "equals" : "equalsIgnoreCase";
        return random.randomBool() ? String.format("%s.%s(%s)", v1, compare, v2)
                : String.format("%s.%s(%s)", v2, compare, v1);
    }

    private String genStringLengthExpr(String name, int min, int max) {
        return name  + ".length()" + genImpossibleIntComparation(min, max, false, false);
    }

    private class StringEquals extends Condition {
        @Override
        String genCondition() {
            return genStringEqualsExpr(pickAndroidString(), popStringField());
        }
    }

    private class SdkVersion extends Condition {

        @Override
        String genCondition() {
            return "Build.VERSION.SDK_INT" + genImpossibleIntComparation(MIN_SDK_INT, MAX_SDK_INT, false, false);
        }
    }

    private class Calendar extends Condition {

        @Override
        String genCondition() {
            loader.addImport("java.util.Calendar");
            return String.format("Calendar.getInstance().get(Calendar.%s)>=%d",
                    random.pick("DAY_OF_WEEK", "DAY_OF_WEEK_IN_MONTH", "WEEK_OF_MONTH", "WEEK_OF_YEAR"), random.randomInt(55, 1000));
        }
    }

    private class StringLength extends Condition {
        @Override
        String genCondition() {
            return genStringLengthExpr(popStringField(), JavaLoader.MIN_STR_LEN, JavaLoader.MAX_STR_LEN);
        }
    }

    private class PackageName extends Condition {
        private final String contextName;
        private PackageName(String n) {
            contextName = n;
        }

        @Override
        String genCondition() {
            int seed = random.randomInt(0, 3);
            String pkg = contextName + ".getPackageName()";
            if (seed == 0) {
                return genStringEqualsExpr(pkg, popStringField());
            } else if (seed == 1)  {
                return genStringEqualsExpr(pkg, pickAndroidString());
            } else {
                return genStringLengthExpr(pkg, 10, 130);
            }
        }
    }

    private class ContextApiNull extends Condition {
        private final String name;
        private ContextApiNull(String n) {
            name = n;
        }

        @Override
        String genCondition() {
            return String.format("%s.%s() == null", name, random.pick(
                    "getFilesDir", "getAssets", "getMainLooper", "getContentResolver",
                    "getApplicationInfo", "getPackageManager", "getResources", "getTheme"));
        }
    }

    private class ApplicationInfo extends Condition {
        private final String name;
        private ApplicationInfo(String n) {
            name = n;
        }

        @Override
        String genCondition() {
            loader.addImport("android.content.pm.ApplicationInfo");
            String k = name + ".getApplicationInfo()";
            return random.pick(
                    String.format("!%s.enabled", k),
                    String.format("%s.uid < 0", k),
                    String.format("%s.targetSdkVersion %s", k, genImpossibleIntComparation(28, MAX_SDK_INT, false, false)),
                    String.format("(%s.flags & ApplicationInfo.FLAG_SYSTEM) != 0", k));
        }
    }
}
