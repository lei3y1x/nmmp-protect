package xhu.spike.fusion;

public abstract class Action {
    public static final String LINE_SEPARATOR = "\\r?\\n";

    protected final JavaLoader loader;
    protected final RandomEngine random;
    public Action(JavaLoader javaLoader) {
        loader = javaLoader;
        random = RandomEngine.getIns();
    }

    public abstract void doAction();

    protected void log(String msg) {
        System.out.println(msg);
    }

    protected String genReturnValue(String type) {
        if (type.equals("String")) {
            String result = random.pick(loader.getNewStrFields());
            return result == null ? "\"" + random.randomName(random.randomInt(3, 8)) + "\"" : result;
        } else if (type.equals("int")) {
            String result = random.pick(loader.getNewIntFields());
            return result == null ? String.valueOf(random.randomInt(1000, 10000)): result;
        } else if (type.equals("long")) {
            return String.valueOf(random.randomInt(1000, 10000));
        } else if (type.equals("byte")) {
            return String.valueOf(random.randomInt(10, 127));
        } else if (type.equals("short")) {
            return String.valueOf(random.randomInt(10, 10000));
        } else if (type.equals("char")) {
            return String.valueOf(random.randomInt(10, 10000));
        } else if (type.equals("float")) {
            return random.randomInt(10, 10000) + "." + random.randomInt(1,99) + "f";
        } else if (type.equals("double")) {
            return random.randomInt(10, 10000) + "." + random.randomInt(1,99);
        } else if (type.equals("boolean")) {
            return random.randomBool() ? "true" : "false";
        } else if (type.equals("void")) {
            return "";
        }
        return "null";
    }
}
