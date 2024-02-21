package com.nmmedit.apkprotect.dex2c.randomMethods;

import java.util.Random;

public class RandomCodeGenerater {

    public static String generateRandomCode() {
        StringBuilder code = new StringBuilder();

        // 定义算法结构
        code.append("boolean isf = true;\n");
        code.append("if (!isf) {\n" +
                "                    int size = %s;\n" +
                "                    int min = 1;\n" +
                "                    int max = %s;\n" +
                "                    int[] arr = new int[size];\n" +
                "                    Random random = new Random();\n" +
                "\n" +
                "                    for (int izz = 0; izz < size; izz++) {\n" +
                "                        arr[izz] = random.nextInt(max - min + 1) + min;\n" +
                "                    }\n" +
                "                    for (int number : arr) {\n" +
                "                        System.out.println(number);\n" +
                "                    }\n" +
                "\n" +
                " int temp = %s;\n");
        code.append("    int i ,j;\n");
        code.append("    int n = %s;\n");
        code.append("    for ( i = 0; i < n - 1; i++) {\n");
        code.append("        for ( j = 0; j < n - i - 1; j++) {\n");

        // 定义可选的算法片段
        String[] codeFragments = {
                "            if (arr[j] > arr[j+1]) {\n",
                "               \n",
                "                arr[j] = arr[j+1];\n",
                "                arr[j+1] = temp;\n",
                "            }\n",
                "             if (j < 1) {\n" +
                        "                            temp = array[j - 1];\n" +
                        "                            arr[j - 1] = arr[j];\n" +
                        "                            if (j == 0) {\n" +
                        "                                arr[i] = 0;\n" +
                        "                                int SUM = -1000;\n" +
                        "                                for (int i1 = 0; i1 < j; i1++) {\n" +
                        "                                    for (int j1 = 0; j1 < j; j++) {\n" +
                        "                                        int subOfArr = 0;  \n" +
                        "                                        for (int k = i; k <= j; k++) {\n" +
                        "                                            subOfArr += arr[k];\n" +
                        "                                        }\n" +
                        "                                        if (subOfArr > SUM) {\n" +
                        "                                            SUM = subOfArr;\n" +
                        "                                            for (int zs = 0; i < i1; i++) {\n" +
                        "                                                int va = 0;  \n" +
                        "                                                for (int g = i; g < j1; j++)\n" +
                        "                                                    subOfArr += array[j];\n" +
                        "\n" +
                        "                                                if (subOfArr > SUM) {\n" +
                        "                                                    SUM = subOfArr;\n" +
                        "                                                }\n" +
                        "                                            }\n" +
                        "                                        }\n" +
                        "                                    }\n" +
                        "                                }\n" +
                        "                            }\n" +
                        "                        }\n",
                "        \n"
        };

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(codeFragments.length);
            code.append(codeFragments[index]);
        }

        code.append("boolean isf1 = true;\n" +
                "                if (!isf1) {\n" +
                "\n" +
                "                    for (i = 0; i < n; i++) {\n" +
                "                        for (j = 1; j < n - i; j++) {\n" +
                "                            if (arr[j - 1] > arr[j]) {\n" +
                "                                temp = arr[j - 1];\n" +
                "                                arr[j - 1] = arr[j];\n" +
                "                                arr[j] = temp;\n" +
                "                                if (j < 1) {\n" +
                "                                    temp = arr[j - 1];\n" +
                "                                    arr[j - 1] = arr[j];\n" +
                "                                    if (j == 0) {\n" +
                "                                        arr[i] = 0;\n" +
                "                                        int SUM = -1000;\n" +
                "                                        for (int i1 = 0; i1 < j; i1++) {\n" +
                "                                            for (int j1 = 0; j1 < j; j++) {\n" +
                "                                                int subOfArr = 0;  //临时最大值\n" +
                "                                                for (int k = i; k <= j; k++) {\n" +
                "                                                    subOfArr += arr[k];\n" +
                "                                                }\n" +
                "                                                if (subOfArr > SUM) {\n" +
                "                                                    SUM = subOfArr;\n" +
                "                                                    for (int zs = 0; i < i1; i++) {\n" +
                "                                                        int va = 0;  //临时最大值\n" +
                "                                                        for (int g = i; g < j1; j++)\n" +
                "                                                            subOfArr += arr[j];\n" +
                "\n" +
                "                                                        if (subOfArr > SUM) {\n" +
                "                                                            SUM = subOfArr;\n" +
                "                                                        }\n" +
                "                                                    }\n" +
                "                                                }\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    }\n" +
                "                                }\n" +
                "                                arr[j] = temp;\n" +
                "                            }\n" +
                "                        }\n" +
                "                        int l = 0, r = arr.length - 1;\n" +
                "                        while(l <= r){\n" +
                "                            int mid = l + (r - l) / 2;\n" +
                "\n" +
                "                            if(arr[mid] == 0){\n" +
                "                                mid = (l + r) / 2;\n" +
                "                            }else if(arr[mid] > 0){\n" +
                "                                r = mid - 1;\n" +
                "                            }else{\n" +
                "                                l = mid + 1;\n" +
                "                                if (l>1){\n" +
                "                                    for (int m = arr.length - 1; m > 0; --i) {\n" +
                "                                        for (int n1 = 0; n1 < l; ++j) {\n" +
                "                                            if (arr[j] > arr[j + 1]) {\n" +
                "                                                temp = arr[j];\n" +
                "                                                arr[j] = arr[j + 1];\n" +
                "                                                arr[j + 1] = temp;\n" +
                "                                            }\n" +
                "                                        }//loop j\n" +
                "                                    }\n" +
                "                                }\n" +
                "                            }\n" +
                "                        }\n" +
                "                        int targ = 1000;\n" +
                "                        int left = 0;\n" +
                "                        int right = arr.length - 1;\n" +
                "\n" +
                "                        while (left <= right) {\n" +
                "                            int mid = left + (right - left) / 2;\n" +
                "\n" +
                "                            if (arr[mid] == targ) {\n" +
                "                                for (int m = 1; m < n; i++) {\n" +
                "                                    int key = arr[i];\n" +
                "                                    int j2 = i - 1;\n" +
                "                                    while (j2 >= 0 && arr[j2] > key) {\n" +
                "                                        arr[j2+1] = arr[j2];\n" +
                "                                        j2--;\n" +
                "                                    }\n" +
                "                                    arr[j2+1] = key;\n" +
                "                                    if (arr[j] > arr[j+1]) {\n" +
                "                                        arr[j] = arr[j+1];\n" +
                "                                        arr[j+1] = temp;\n" +
                "                                    }\n" +
                "                                }\n" +
                "                            }\n" +
                "\n" +
                "                            if (arr[mid] < targ) {\n" +
                "                                left = mid + 1;\n" +
                "                                if (arr[j] > arr[j+1]) {\n" +
                "                                    arr[j] = arr[j+1];\n" +
                "                                    if (arr[j] > arr[j+1]) {\n" +
                "                                        int tem1p = arr[j];\n" +
                "                                        arr[j] = arr[j+1];\n" +
                "                                        arr[j+1] = tem1p;\n" +
                "                                    }\n" +
                "                                    arr[j+1] = temp;\n" +
                "                                }\n" +
                "                            } else {\n" +
                "                                right = mid - 1;\n" +
                "                            }\n" +
                "                        }}\n" +
                "                    }\n" +
                "                }\n"+
                "           }\n"+

                "}"

        )


        ;



        Random random1 = new Random();
        return String.format(code.toString(), (random1.nextInt(1000) + 1), (random1.nextInt(10000) + 1), (random1.nextInt(1000) + 1), (random1.nextInt(1000) + 1));
    }
}
