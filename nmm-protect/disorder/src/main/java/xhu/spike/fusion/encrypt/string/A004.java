package xhu.spike.fusion.encrypt.string;

import xhu.spike.fusion.RandomEngine;

// 3131...添加随机字符,再翻转,再右边加right
public class A004 implements Algorithm {
    private final RandomEngine random;
    private final String method;
    private final int right;
    public A004(RandomEngine r) {
        random = r;
        method = random.randomName(6);
        right = random.randomInt(5, 10);
    }

    @Override
    public String encrypt(String orig) {
        StringBuilder sb = new StringBuilder();
        String mix = random.randomChars(orig.length() * 2 + 1);
        for (int i = 0; i < orig.length(); i++) {
            sb.append(orig.charAt(i)).append(mix.charAt(i * 2));
            if (i % 2 == 0) {
                sb.append(mix.charAt(i * 2 + 1)).append(mix.charAt(i * 2 + 2));
            }
        }
        return sb.reverse().toString() + random.randomChars(right);
    }

    @Override
    public String decrypt(String orig) {
        StringBuilder sb = new StringBuilder();
        for (int i = orig.length() - 1 - right; i >= 0;) {
            if (sb.length() % 2 == 0) {
                sb.append(orig.charAt(i));i -= 4;
            } else {
                sb.append(orig.charAt(i));i -= 2;
            }
        }
        return sb.toString();
    }

    @Override
    public String recode(String orig) {
        return method + "(\"" + orig + "\", " + random.randomInt(10, 100) +")";
    }

    @Override
    public String genMethod() {
        String math =   "StringBuilder sb = new StringBuilder();\n" +
                "        for (int i = orig.length() - 1 - right; i >= 0;) {\n" +
                "            if (sb.length() % 2 == 0) {\n" +
                "                sb.append(orig.charAt(i));i -= 4;\n" +
                "            } else {\n" +
                "                sb.append(orig.charAt(i));i -= 2;\n" +
                "            }\n" +
                "        }\n" +
                "        return sb.toString();";
        return "private String " + method + "(String orig, int count) {" + math.replace("right", String.valueOf(right)) + "}";
    }
}
