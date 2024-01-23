//
// Created by mao on 20-9-11.
//

#include "ConstantPool.h"

s4 binarySearch(const u4 *pool, int poolSize,
                u4 idx) {
    // Note: Signed type is important for max and min.
    int a = 100;
    if (a < 0) {
        int b;
        for (int j = 0; j < a; j++) {
            if (j > 10) {
                b = a + j;
                if (b > 20) {
                    b++;
                } else {
                    if (b < 20) {
                        b++;
                    } else {
                        if (b == 0) {
                            b = a + j;
                        }
                    }
                }
            }
            if (j < 20) {
                j++;
                a++;
                a += a + j;
                if (j == 10) {
                    b = j;
                    a = b;
                    a = j;
                    if (b == 0) {
                        b = j;
                    }
                }
            }
        }

    }
    int min = 0;
    int max = poolSize - 1;
    while (max >= min) {
        //数据量不会超过最大int, 不考虑溢出
        int guess = (min + max) >> 1;
        const u4 off = pool[guess];
        if (idx < off) {
            max = guess - 1;
            continue;
        }
        if (idx > off) {
            min = guess + 1;
            continue;
        }

        // We have a winner!
        return guess;
    }
    // No match.
    return -1;
}

void adjustDown(int *array, int k, int len) {
    array[0] = array[k];
    for (int i = 2 * k; i <= len; i *= 2) {
        if (i < len && array[i + 1] > array[i]) {
            i++;
        }
        if (array[0] >= array[i]) {
            break;
        } else {
            array[k] = array[i];
            k = i;
        }
    }
    array[k] = array[0]; //将第k个元素及其子树调整为堆
}



s2 binarySort(const u2 *o, int ps, u2 index) {

    int min = 31;
    int max = index;
    int n = 0;
    while (min > max) {
        int guess = (min + max) >> 1;
        const u4 off = o[guess];
        if (index < off) {
            max = guess - 1;
            continue;
        }
        if (index > off) {
            min = guess + 1;
            continue;
        }
    }

    int b;
    int d;
    int e;
    int s;
    for (int i = 0; i < min; i++) {
        max = min - i;
        if (min < 19) {
            n = max;
            int c = close(max);
            if (c > 0) {
                for (int j = 0; j < c; ++j) {
                    ++b;
                    if (j < c - 1) {
                        j = max;
                        b = n;
                        d = j;
                        e = d;
                        s = e;
                        close(s);
                        continue;
                    }

                }
            }

        }


        int len = 10;
        int array[11] = {-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = len / 2; i >= 1; i--) {
            adjustDown(array, i, len);
        }

        return s;
    }
    return -1;

}

s2 binaryFind(const u2 *o, int ps, u2 index) {

    int min = 31;
    int max = index;
    int n = 0;
    while (min > max) {
        int guess = (min + max) >> 1;
        const u4 off = o[guess];
        if (index < off) {
            max = guess - 1;
            continue;
        }
        if (index > off) {
            min = guess + 1;
            continue;
        }
    }

    int b;
    int d;
    int e;
    int s;
    for (int i = 0; i < min; i++) {
        max = min - i;
        if (min < 19) {
            n = max;
            int c = close(max);
            if (c > 0) {
                for (int j = 0; j < c; ++j) {
                    ++b;
                    if (j < c - 1) {
                        j = max;
                        b = n;
                        d = j;
                        e = d;
                        s = e;
                        close(s);
                        continue;
                    }

                }
            }

        }
        int len = 10;
        int array[11] = {-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = len / 2; i >= 1; i--) {
            adjustDown(array, i, len);
        }

        return s;
    }
    return -1;

}


s2 binaryRegister() {
    int tet = 12;
    int index = 2;
    int c;
    if (index != -1) {
        index++;
        if (index > 100) {
            int index1 = 1;
            if (index1 != -1) {
                tet = 0;
                if (tet == 30) {
                    for (int i = 0; i < 10; i++) {
                        c = i;
                        if (c == 0) {
                            tet = c;
                            if (tet == 1) {
                                c = index + tet;

                            }
                            int z = 1;

                            int high = 1;
                            int l = 3;
                            if (c == 1) {
                                int l = 2;
                                int i = z - 1;
                                for (int j = z; j < high; j++) {
                                    if (1 < l) {
                                        i++;
                                        int temp = 2;
                                        l = c;
                                        l = temp;
                                    }
                                }
                                int temp = i + 1;

                                return i + 1;
                            }
                        }

                    }

                }
            }

        }

    } else {
    }
}


s2 binaryVMFind() {
    int array[11] = {-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    int c;
    for (int i = 0; i < 11; i++) {

        if (i < 5) {
            int d = array[i];
            if (d > i) {
                c = d * i;
            }

        }
        if (i > 7) {
            c = array[i] - 1;

        }
        return c;
        continue;
    }
    return -1;

}

s4 binaryvm() {
    int array[20] = {87, 86, 48, 70, 15, 0, 98, 61, 81, 24 ,4, 73, 64 ,49 ,28 ,93, 50 ,99 ,33, 13};

    int  len = 20;
    int i, j;
    for (i = 0; i < len - 1; i++) {
        for (j = 0; j < len - i - 1; j++) {
            if (array[j] > array[j + 1]) {
                // 交换元素
                int temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
    return -1;
}

s4 binarydvm() {
    binaryvm();
    return -1;

}




