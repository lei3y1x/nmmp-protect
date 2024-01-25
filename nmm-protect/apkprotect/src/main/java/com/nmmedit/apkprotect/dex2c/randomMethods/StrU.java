package com.nmmedit.apkprotect.dex2c.randomMethods;

import com.android.tools.r8.internal.S;
import com.nmmedit.apkprotect.dex2c.DexConfig;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

public class StrU {



   public static String codeStr =
           "int Java_%sa_s%s_d%s_z%s%d(JNIEnv *env, jobject thiz) {\n" +
            "\n" +
            "//\n" +
            "    int k;\n" +
            "   \n" +
            "    for (int i = %d / 2; i >= 1; i--) {\n" +
            "        k = i;\n" +
            "    }\n" +
            "    \n" +
            "  int randomNumber =%d;\n" +
            "    int array[randomNumber];\n" +
            "    for (int i = 0; i < randomNumber; i++) {\n" +
            "        array[i] = i+3;\n" +
            "    }\n" +
            "\n" +
            "    array[0] = array[k];\n" +
            "    for (int i = 2 * k; i <= %d; i *= 2) {\n" +
            "        if (i < %d && array[i + 1] > array[i]) {\n" +
            "            i++;\n" +
            "        }\n" +
            "\n" +
            "        if (array[0] >= array[i]) {\n" +
            "            break;\n" +
            "        } else {\n" +
            "            array[k] = array[i];\n" +
            "            k = i;\n" +
            "        }\n" +
            "    }\n" +
            "    array[k] = array[0];\n" +
            "\n" +
            "   \n" +
            "    for (int i = 0; i < %d; i++) {\n" +
            "        if (%d<10){\n" +
            "            array[i] = %d + %d;\n" +
            "        }else {\n" +
            "            array[i] = %d + %d;\n" +
            "        }\n" +
            "    }\n" +
            "    for (int i = 0; i < %d - 1; i++) {\n" +
            "        for (int j = 0; j < %d - i - 1; j++) {\n" +
            "            if (array[j] > array[j + 1]) {\n" +
            "                int temp = array[j];\n" +
            "                array[j] = array[j + 1];\n" +
            "                array[j + 1] = temp;\n" +
            "                if (j<0){\n" +
            "                    array[j] = %d;\n" +
            "                    if (%d>10){\n" +
            "                        j--;\n" +
            "                    }\n" +
            "                }\n" +
            "                if (j<10){\n" +
            "                    array[j] = %d;\n" +
            "                    if (%d>10){\n" +
            "                        j--;\n" +
            "                    }\n" +
            "                }\n" +
            "                if (j<20){\n" +
            "                    array[j] = %d;\n" +
            "                    if (%d>10){\n" +
            "                        j--;\n" +
            "                    }\n" +
            "                }\n" +
            "                if (j<30){\n" +
            "                    array[j] = %d;\n" +
            "                    if (%d>10){\n" +
            "                        j--;\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    for (int i = 1; i < %d; i++) {\n" +
            "        int key = array[i];\n" +
            "        int j = i - 1;\n" +
            "\n" +
            "        while (j >= 0 && array[j] > key) {\n" +
            "            array[j + 1] = array[j];\n" +
            "            j--;\n" +
            "            if (j<0){\n" +
            "                array[j] = %d;\n" +
            "                if (%d>10){\n" +
            "                    j--;\n" +
            "                }\n" +
            "            }\n" +
            "            if (j<10){\n" +
            "                array[j] = %d;\n" +
            "                if (%d>10){\n" +
            "                    j--;\n" +
            "                }\n" +
            "            }\n" +
            "            if (j<20){\n" +
            "                array[j] = %d;\n" +
            "                if (%d>10){\n" +
            "                    j--;\n" +
            "                }\n" +
            "            }\n" +
            "            if (j<30){\n" +
            "                array[j] = %d;\n" +
            "                if (%d>10){\n" +
            "                    j--;\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "        array[j + 1] = key;\n" +
            "    }\n" +

            "  return JNI_FALSE;\n" +
           "}\n"
            + "\n"
            + "\n";




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



