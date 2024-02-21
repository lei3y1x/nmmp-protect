package xhu.spike.fusion.encrypt.string;

import xhu.spike.fusion.RandomEngine;

//倒转,每个字符后加(deep-1)个随机字符,然后在左边加left个随机字符,右边加right个随机字符
public class A001 implements Algorithm {
    private final int deep;
    private final int left;
    private final int right;
    private final String method;
    private final RandomEngine random;
    public A001(RandomEngine r) {
        random = r;
        deep = random.randomInt(3, 6);
        left = random.randomInt(5, 10);
        right = random.randomInt(5, 10);
        method = random.randomName(6);
    }

    @Override
    public String encrypt(String orig) {
        StringBuilder sb = new StringBuilder();
        int extraTimes = deep - 1;
        String mix = random.randomChars(orig.length() * extraTimes);
        for (int i = orig.length() - 1; i >= 0; i--) {
            sb.append(orig.charAt(i));
            for (int j = 0; j < extraTimes; j++) {
                sb.append(mix.charAt(i * extraTimes + j));
            }
        }
        return random.randomChars(left) + sb.toString() + random.randomChars(right);
    }

    @Override
    public String decrypt(String orig) {
        // ---------- divider ----------
        orig = orig.substring(left, orig.length() - right);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < orig.length() / deep; i++) {
            sb.insert(0, orig.charAt(i * deep));
        }
        return sb.toString();
    }

    @Override
    public String recode(String orig) {
        return method + "(\"" + orig + "\")";
    }

    @Override
    public String genMethod() {
        String math =   "        orig = orig.substring(" + left + ", orig.length() - " + right + ");\n" +
                        "        StringBuilder sb = new StringBuilder();\n" +
                        "        for (int i = 0; i < orig.length() / deep; i++) {\n" +
                        "            sb.insert(0, orig.charAt(i * deep));\n" +
                        "        }\n" +
                        "        return sb.toString();";
        return "private String " + method + "(String orig) {int deep = " + deep + ";" + math + "}";
    }
}
