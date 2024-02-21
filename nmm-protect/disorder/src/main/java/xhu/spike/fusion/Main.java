package xhu.spike.fusion;

import xhu.spike.fusion.encrypt.digit.DigitEncryption;
import xhu.spike.fusion.encrypt.string.StringEncryption;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        process();
    }

    private static void process() {
        JavaVisitor visitor = new JavaVisitor(new File(System.getProperty("user.home"), "private/com/sdk/code/"));
        File[] files = visitor.getJavaFiles();
        for (File f : files) {
            processFile(f);
        }
    }

    private static void processFile(File f) {
        log("Start to process file: " + f.getAbsolutePath());
        log("Original File Size: " + f.length());

        try {
            RandomEngine.reset();
            RandomEngine random = RandomEngine.getIns();
            JavaLoader jl = new JavaLoader(f);
            Action[] actions = new Action[] {
                    new AdjustCode(jl),
                    new DigitEncryption(jl),
                    new StringEncryption(jl),
                    new InitFields(jl, random.randomInt(5, 10)),
                    new InitMethods(jl, random.randomInt(5, 10)),
                    new ReturnDistraction(jl, 5),
                    new StrDistraction(jl, 5),
                    new IntDistraction(jl, 5),
                    new ReturnDistraction(jl, 10),
                    new LogDistraction(jl, 10),
            };
            for (Action a : actions) {
                a.doAction();
            }
            jl.dump();
            log("Finished File Size: " + f.length());
        } catch (Exception e) {
            Tools.log(e.toString());
        }
    }

    private static void log(String msg) {
        Tools.log("[Disorder] " + msg);
    }
}
