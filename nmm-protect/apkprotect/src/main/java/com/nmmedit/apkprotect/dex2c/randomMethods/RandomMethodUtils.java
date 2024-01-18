package com.nmmedit.apkprotect.dex2c.randomMethods;

import com.nmmedit.apkprotect.dex2c.DexConfig;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class RandomMethodUtils {

    public void generateJniRandom(Writer writer, ArrayList<DexConfig> configs) throws IOException {
        final StringBuilder includeStaOrExternFunc = new StringBuilder();
        final StringBuilder initCallSta = new StringBuilder();
        for (DexConfig config : configs) {
            final DexConfig.HeaderFileAndSetupFuncName setupFunc = config.getHeaderFileAndSetupFunc();
            includeStaOrExternFunc.append(String.format("extern void %s(JNIEnv *env);\n", setupFunc.setupFunctionName));
            initCallSta.append(String.format("    %s(env);\n", setupFunc.setupFunctionName));
        }
        String string = "\n" +
                "Java_com_promt_ads_AdMobInterstitial_compileSharerResource0(JavaVM *vm, jobject thiz,\n" +
                "                                                            jstring string, jint bs,\n" +
                "                                                            jobject cookie) {\n"  +
                "}";
        writer.write(String.format(string, includeStaOrExternFunc.toString(), initCallSta.toString()));
    }


    public static
    void  generCode(List<DexConfig> lists){
        final  StringBuilder sb = new StringBuilder();

        for (DexConfig config :lists){
            final  DexConfig.HeaderFileAndSetupFuncName setFun = config.getHeaderFileAndSetupFunc();
            sb.append("extren %s (JNIEnv *e);");

        }
    }
}
