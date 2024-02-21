package xhu.spike.fusion.encrypt.string;

import xhu.spike.fusion.RandomEngine;

// ascii编码 35-126内循环
public class A006 implements Algorithm {
    private final RandomEngine random;
    private final String method;
    private final int cut;
    public A006(RandomEngine r) {
        random = r;
        method = random.randomName(6);
        cut = random.randomInt(5, 20);
    }

    @Override
    public String encrypt(String orig) {
        StringBuilder sb = new StringBuilder();
        String mix = random.randomChars(orig.length());
        for (int i = 0; i < orig.length(); i++) {
            sb.append(mix.charAt(i));
            int code = orig.charAt(i);
            if (code >= 35 && code <= 126) {
                code += cut;
                if (code > 126) {
                    code = code - 92;
                }
            }
            sb.append((char) code);
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String orig) {
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < orig.length() / 2; i++) {
            int code = orig.charAt(i * 2 + 1);
            if (code >= 35 && code <= 126) {
                code -= cut;
                if (code < 35) {
                    code += 92;
                }
            }
            x.append((char) code);
        }
        return x.toString();
    }

    @Override
    public String recode(String orig) {
        return method + "(\"" + orig + "\"" +")";
    }

    @Override
    public String genMethod() {
        String math =   "StringBuilder x = new StringBuilder();\n" +
                "        for (int i = 0; i < orig.length() / 2; i++) {\n" +
                "            int code = orig.charAt(i * 2 + 1);\n" +
                "            if (code >= 35 && code <= 126) {\n" +
                "                code -= cut;\n" +
                "                if (code < 35) {\n" +
                "                    code += 92;\n" +
                "                }\n" +
                "            }\n" +
                "            x.append((char) code);\n" +
                "        }\n" +
                "        return x.toString();";
        return "private String " + method + "(String orig) {" + math.replace("cut", String.valueOf(cut)) + "}";
    }
}
