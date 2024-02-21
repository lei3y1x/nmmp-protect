package xhu.spike.fusion.encrypt.string;

import xhu.spike.fusion.RandomEngine;

// 每cut个数倒转,并每个字符加一个随机字符
public class A002 implements Algorithm {
    private final int cut;
    private final String method;
    private final RandomEngine random;
    public A002(RandomEngine r) {
        random = r;
        cut = random.randomInt(3, 6);
        method = random.randomName(6);
    }

    @Override
    public String encrypt(String orig) {
        StringBuilder sb = new StringBuilder();
        String mix = random.randomChars(orig.length());
        for (int i = 0; i <= orig.length() / cut; i++) {
            for (int j = cut - 1; j >= 0; j--) {
                int index = i * cut + j;
                if (index < orig.length()) {
                    sb.append(orig.charAt(index)).append(mix.charAt(sb.length() / 2));
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String orig) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= orig.length() / cut / 2; i++) {
            for (int j = cut - 1; j >= 0; j--) {
                int index = (i * cut + j) * 2;
                if (index < orig.length()) {
                    sb.append(orig.charAt(index));
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String recode(String orig) {
        return method + "(\"" + orig + "\")";
    }

    @Override
    public String genMethod() {
        String math =   "StringBuilder sb = new StringBuilder();\n" +
                "        for (int i = 0; i <= orig.length() / cut / 2; i++) {\n" +
                "            for (int j = cut - 1; j >= 0; j--) {\n" +
                "                int index = (i * cut + j) * 2;\n" +
                "                if (index < orig.length()) {\n" +
                "                    sb.append(orig.charAt(index));\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        return sb.toString();";
        return "private String " + method + "(String orig) {" + math.replace("cut", String.valueOf(cut)) + "}";
    }
}
