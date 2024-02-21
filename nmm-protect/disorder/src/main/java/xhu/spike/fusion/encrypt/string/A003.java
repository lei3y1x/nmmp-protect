package xhu.spike.fusion.encrypt.string;

import xhu.spike.fusion.RandomEngine;

// 下半区每个字符放在上半区每个字符前面,再给所有字符加一个随机字符
public class A003 implements Algorithm {
    private final RandomEngine random;
    private final int left;
    private final String method;
    public A003(RandomEngine r) {
        random = r;
        left = random.randomInt(10, 20);
        method = random.randomName(6);
    }

    @Override
    public String encrypt(String orig) {
        StringBuilder sb = new StringBuilder();
        int half = orig.length() / 2;
        for (int i = 0; i < half; i++) {
            sb.append(orig.charAt(half + i)).append(orig.charAt(i));
        }
        if (orig.length() % 2 == 1) {
            sb.append(orig.charAt(orig.length() - 1));
        }
        String mix = random.randomChars(orig.length());
        for (int i = 0; i < orig.length(); i++) {
            int index = i * 2 + 1;
            if (index >= sb.length()) {
                sb.append(mix.charAt(i));
            } else {
                sb.insert(index, mix.charAt(i));
            }
        }
        return random.randomChars(left) + sb.toString();
    }

    @Override
    public String decrypt(String orig) {
        StringBuilder front = new StringBuilder();
        StringBuilder end = new StringBuilder();
        orig = orig.substring(left);
        for (int i = 0; i < orig.length() / 2; i++) {
            if (i % 2 == 0) {
                end.append(orig.charAt(i * 2));
            } else {
                front.append(orig.charAt(i * 2));
            }
        }
        return front.append(end).toString();
    }

    @Override
    public String recode(String orig) {
        return method + "(\"" + orig + "\", " + random.randomInt(10, 100) +")";
    }

    @Override
    public String genMethod() {
        String math =   "StringBuilder front = new StringBuilder();\n" +
                "        StringBuilder end = new StringBuilder();\n" +
                "        orig = orig.substring(left);\n" +
                "        for (int i = 0; i < orig.length() / 2; i++) {\n" +
                "            if (i % 2 == 0) {\n" +
                "                end.append(orig.charAt(i * 2));\n" +
                "            } else {\n" +
                "                front.append(orig.charAt(i * 2));\n" +
                "            }\n" +
                "        }\n" +
                "        return front.append(end).toString();";
        return "private String " + method + "(String orig, int count) {" + math.replace("left", String.valueOf(left)) + "}";
    }
}
