package xhu.spike.fusion.encrypt.string;

import xhu.spike.fusion.RandomEngine;

// cut个区各自翻转,每个字符前加1字符
public class A005 implements Algorithm {
    private final RandomEngine random;
    private final String method;
    private final int cut;
    public A005(RandomEngine r) {
        random = r;
        method = random.randomName(6);
        cut = random.randomInt(2, 6);
    }

    @Override
    public String encrypt(String orig) {
        StringBuilder sb = new StringBuilder();
        if (cut < orig.length()) {
            int size = orig.length() / cut;
            for (int i = 0; i < cut; i++) {
                for (int j = size - 1; j >= 0; j--) {
                    sb.append(orig.charAt(i * size + j));
                }
            }
            int extra = orig.length() % cut;
            for (int i = 0; i < extra; i++) {
                sb.append(orig.charAt(orig.length() - i -1));
            }
        } else {
            sb.append(orig).reverse();
        }
        String mix = random.randomChars(orig.length());
        for (int i = 0; i < mix.length(); i++) {
            sb.insert(i * 2, mix.charAt(i));
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String orig) {
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < orig.length() / 2; i++) {
            x.append(orig.charAt(i * 2 + 1));
        }
        orig = x.toString();
        StringBuilder sb = new StringBuilder();
        if (cut < orig.length()) {
            int size = orig.length() / cut;
            for (int i = 0; i < cut; i++) {
                for (int j = size - 1; j >= 0; j--) {
                    sb.append(orig.charAt(i * size + j));
                }
            }
            int extra = orig.length() % cut;
            for (int i = 0; i < extra; i++) {
                sb.append(orig.charAt(orig.length() - i - 1));
            }
        } else {
            sb.append(orig).reverse();
        }
        return sb.toString();
    }

    @Override
    public String recode(String orig) {
        return method + "(\"" + orig + "\"" +")";
    }

    @Override
    public String genMethod() {
        String math =   "StringBuilder x = new StringBuilder();\n" +
                "        for (int i = 0; i < orig.length() / 2; i++) {\n" +
                "            x.append(orig.charAt(i * 2 + 1));\n" +
                "        }\n" +
                "        orig = x.toString();\n" +
                "        StringBuilder sb = new StringBuilder();\n" +
                "        if (cut < orig.length()) {\n" +
                "            int size = orig.length() / cut;\n" +
                "            for (int i = 0; i < cut; i++) {\n" +
                "                for (int j = size - 1; j >= 0; j--) {\n" +
                "                    sb.append(orig.charAt(i * size + j));\n" +
                "                }\n" +
                "            }\n" +
                "            int extra = orig.length() % cut;\n" +
                "            for (int i = 0; i < extra; i++) {\n" +
                "                sb.append(orig.charAt(orig.length() - i - 1));\n" +
                "            }\n" +
                "        } else {\n" +
                "            sb.append(orig).reverse();\n" +
                "        }\n" +
                "        return sb.toString();";
        return "private String " + method + "(String orig) {" + math.replace("cut", String.valueOf(cut)) + "}";
    }
}
