package xhu.spike.fusion.encrypt.string;

import xhu.spike.fusion.RandomEngine;
import xhu.spike.fusion.Tools;

public class AlgorithmTest {
    public static void run() {
        RandomEngine random = RandomEngine.getIns();
        Algorithm[] algorithms = new Algorithm[] {
                new A001(random),
                new A002(random),
                new A003(random),
                new A004(random),
                new A005(random),
                new A006(random),
        };
        for (Algorithm a : algorithms) {
            testAlgorithm(a);
        }
    }

    private static void testAlgorithm(Algorithm a) {
        String demo = "https://xn3o.oss-accelerate.aliyuncs.com/xn3o";
        String en = a.encrypt(demo);
        log("--------------------------------------------------------------------------------");
        log("Test Algorithm: " + a.getClass().getSimpleName());
        log("orig demo: " + demo);
        log("encrypted: " + en);
        String de = a.decrypt(en);
        log("decrypted: " + a.decrypt(en));
        log("result is: " + (de.equals(demo) ? "Success" : "Failed"));
    }

    private static void log(String msg) {
        Tools.log(msg);
    }
}
