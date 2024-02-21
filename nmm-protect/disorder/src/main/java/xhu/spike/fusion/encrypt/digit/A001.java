package xhu.spike.fusion.encrypt.digit;

import xhu.spike.fusion.RandomEngine;

public class A001 implements Algorithm {
    private final int delta;
    private final String method;
    private final RandomEngine random;
    public A001(RandomEngine r) {
        random = r;
        delta = random.randomInt(1000, 2000);
        method = random.randomName(6);
    }

    @Override
    public int encrypt(int orig) {
        return orig + delta;
    }

    @Override
    public int decrypt(int orig) {
        return orig - delta;
    }

    @Override
    public String recode(int orig) {
        return method + "(" + orig + ")";
    }

    @Override
    public String genMethod() {
        String math =   "        return orig - " + delta + ";";
        return "private int " + method + "(int orig) {" + math + "}";
    }
}
