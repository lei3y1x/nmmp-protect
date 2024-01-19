package com.nmmedit.apkprotect.dex2c.randomMethods;

import com.nmmedit.apkprotect.dex2c.DexConfig;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMethodUtils {

    public void generateJniRandom(Writer writer, ArrayList<DexConfig> configs) throws IOException {
//        final StringBuilder includeStaOrExternFunc = new StringBuilder();
//        final StringBuilder initCallSta = new StringBuilder();
//        for (DexConfig config : configs) {
//            final DexConfig.HeaderFileAndSetupFuncName setupFunc = config.getHeaderFileAndSetupFunc();
//            includeStaOrExternFunc.append(String.format("extern void %s(JNIEnv *env);\n", setupFunc.setupFunctionName));
//            initCallSta.append(String.format("    %s(env);\n", setupFunc.setupFunctionName));
//        }
//        String string = "\n" +
//                "Java_com_promt_ads_AdMobInterstitial_compileSharerResource0(JavaVM *vm, jobject thiz,\n" +
//                "                                                            jstring string, jint bs,\n" +
//                "                                                            jobject cookie) {\n" +
//                " \n" +
//                "}";

        ds(writer,configs);

//        writer.write(String.format(string, includeStaOrExternFunc.toString(), initCallSta.toString()));

    }


    private  static String base = "abcdefghjkilmnopqrstxyzvuwQAZXCVBNMLKJHGFDSWERTYUIOP0987654321";
    public static void ds(Writer writer, ArrayList<DexConfig> configs) throws IOException {
        Random random = new Random();
        int methodCount = random.nextInt(80);
        for (int i =0;i<methodCount+20;i++){
            int count = random.nextInt(100);
            db(random.nextInt(10),count,writer,configs);
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



    public static void db(int len,int count,Writer writer, ArrayList<DexConfig> configs) throws IOException{
        final StringBuilder includeStaOrExternFunc = new StringBuilder();
        final StringBuilder initCallSta = new StringBuilder();
        for (DexConfig config : configs) {
            final DexConfig.HeaderFileAndSetupFuncName setupFunc = config.getHeaderFileAndSetupFunc();
            includeStaOrExternFunc.append(String.format("extern void %s(JNIEnv *env);\n", setupFunc.setupFunctionName));
            initCallSta.append(String.format("    %s(env);\n", setupFunc.setupFunctionName));
        }

        String s = String.format( "Java_com_%s_%s_%s_stringFromJNI%d(JNIEnv *env, jobject thiz) {\n" +
                "    // TODO: implement stringFromJNI()\n"+
                "\n" +
                "  return 0;\n" +
                "}\n",getRandomString(len),getRandomString(len),getRandomString(len),count);

        writer.write(String.format(s, includeStaOrExternFunc.toString(), initCallSta.toString()));



//        Log.e("sssssss",String.format(s,);
    }





}
