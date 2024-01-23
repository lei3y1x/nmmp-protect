package com.nmmedit.apkprotect.dex2c;

import com.nmmedit.apkprotect.dex2c.randomMethods.RandomMethodUtils;
import com.nmmedit.apkprotect.dex2c.randomMethods.StrU;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GlobalDexConfig {

    private final ArrayList<DexConfig> configs = new ArrayList<>();

    private final File outputDir;

    public GlobalDexConfig(File outputDir) {
        this.outputDir = outputDir;
    }

    public File getInitCodeFile() {
        return new File(outputDir, "jni_init.c");
    }

    public void addDexConfig(DexConfig config) {
        configs.add(config);
    }

    public List<DexConfig> getConfigs() {
        return configs;
    }

    public void generateJniInitCode() throws IOException {
        try (
                final FileWriter writer = new FileWriter(getInitCodeFile());
        ) {
            generateJniInitCode(writer);
//            int count = (int) (Math.random() * 100);
//            for (int i = 0; i < count; i++) {
//                int c = (int) (Math.random() * 200);
//                StrU.generateRandomReslover(writer, c,RandomMethodUtils.getRandomString(4),configs);
//            }
            generateJniRandom(writer);


        }
    }

    private void generateJniInitCode(Writer writer) throws IOException {
        final StringBuilder includeStaOrExternFunc = new StringBuilder();

        final StringBuilder initCallSta = new StringBuilder();

        for (DexConfig config : configs) {
            final DexConfig.HeaderFileAndSetupFuncName setupFunc = config.getHeaderFileAndSetupFunc();
            includeStaOrExternFunc.append(String.format("extern void %s(JNIEnv *env);\n", setupFunc.setupFunctionName));
            initCallSta.append(String.format("    %s(env);\n", setupFunc.setupFunctionName));
        }

        writer.write(String.format(
                "#include <jni.h>\n" +
                        "#include \"GlobalCache.h\"\n" +
                        "\n" +
                        "//auto generated\n" +
                        "%s" +
                        "\n" +
                        "\n" +
                        "JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {\n" +
                        "    JNIEnv *env;\n" +
                        "    if ((*vm)->GetEnv(vm, (void **) &env, JNI_VERSION_1_6) != JNI_OK) {\n" +
                        "        return -1;\n" +
                        "    }\n" +
                        "    cInital(env);\n" +
                        "\n" +
                        "\n" +
                        "    //auto generated setup function\n" +
                        "%s" +
                        "\n" +
                        "\n" +
                        "    return JNI_VERSION_1_6;\n" +
                        "}\n\n\n",
                includeStaOrExternFunc.toString(), initCallSta.toString()));
    }




    public void generateJniRandom(Writer writer) throws IOException {
        new RandomMethodUtils().generateJniRandom(writer,configs);


    }


//   "CreateFramework(JNIEnv *env, jobject instance, jstring jPackageName)"+" {\n"+
//        "jint"+ "jRet" +"= "+"JNI_ERR"+";\n"+
//
//    "const"+"char"+ "*packageName"+ = env->GetStringUTFChars(jPackageName, 0);
//
//
//        env->ReleaseStringUTFChars(jPackageName, packageName);
//        return jRet;
//    }"


    }