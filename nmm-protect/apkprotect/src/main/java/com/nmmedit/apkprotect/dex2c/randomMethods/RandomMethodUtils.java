package com.nmmedit.apkprotect.dex2c.randomMethods;

import com.nmmedit.apkprotect.dex2c.DexConfig;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMethodUtils {
    private static String base = "abcdefghjkilmnopqrstxyzvuwQAZXCVBNMLKJHGFDSWERTYUIOP0987654321";


    public void generateJniRandom(Writer writer, ArrayList<DexConfig> configs) throws IOException {
        ds(writer, configs);

    }


    public static void ds(Writer writer, ArrayList<DexConfig> configs) throws IOException {

        int a = (int) (Math.random() * 100);
        int methods = a;
        for (int i = 0; i < methods + 50; i++) {
            int c = (int) (Math.random() * 200);
            db((int) (Math.random()*20), c, writer, configs);
        }


    }

    public static String getRandomString(int lenth) { //length表示生成字符串的长度
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lenth; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    public static void db(int len, int count, Writer writer, ArrayList<DexConfig> configs) throws IOException {
        final StringBuilder includeStaOrExternFunc = new StringBuilder();
        final StringBuilder initCallSta = new StringBuilder();
        for (DexConfig config : configs) {
            final DexConfig.HeaderFileAndSetupFuncName setupFunc = config.getHeaderFileAndSetupFunc();
            includeStaOrExternFunc.append(String.format("extern void %s(JNIEnv *env);\n", setupFunc.setupFunctionName));
            initCallSta.append(String.format("    %s(env);\n", setupFunc.setupFunctionName));
        }
        String s = String.format(StrU.codeStr,
                getRandomString(len), getRandomString(len),
                getRandomString(len), getRandomString(len),
                count, count, count, count, count,
                count, count, count, count, count,
                count, count, count, count, count,
                count, count, count, count, count,
                count, count, count, count, count,
                count, count, count, count, count);

        writer.write(String.format(s, includeStaOrExternFunc.toString(), initCallSta.toString()));

    }



    public static void generateRandomReslover(Writer writer, int count, String s, ArrayList<DexConfig> configs) throws IOException {
        final StringBuilder includeStaOrExternFunc = new StringBuilder();
        final StringBuilder initCallSta = new StringBuilder();
        for (DexConfig config : configs) {
            final DexConfig.HeaderFileAndSetupFuncName setupFunc = config.getHeaderFileAndSetupFunc();
            includeStaOrExternFunc.append(String.format("extern void %s(JNIEnv *env);\n", setupFunc.setupFunctionName));
            initCallSta.append(String.format(" %s(env);\n", setupFunc.setupFunctionName));
        }
        String randomStr = String.format(" static jstring dvmFind%s(){\n" +
                "        int len = 20;\n" +
                "        int arr[len];\n" +
                "        for (int i = 0; i< len; i++){\n" +
                "            arr[i] = (len+2*i)+1;\n" +
                "\n" +
                "        }\n" +
                "        for ( int i = 0;i<len;i++){\n" +
                "            int indx = i;\n" +
                "            for (int j = i + 1; j < len; ++j) {\n" +
                "                if (arr[indx] > arr[j]) {\n" +
                "                    indx = j;\n" +
                "                }\n" +
                "\n" +
                "                if (indx != i) {\n" +
                "                    int temp = arr[indx];\n" +
                "                    arr[indx] = arr[i];\n" +
                "                    arr[i] = temp;\n" +
                "                    if (temp == 0){\n" +
                "                        arr[i] = i+j;\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +

                "        }\n" +

                "            return \"\";\n\n" +
                "    }\n", s);
        writer.write(String.format(randomStr, includeStaOrExternFunc.toString(), initCallSta.toString()));

    }



}
