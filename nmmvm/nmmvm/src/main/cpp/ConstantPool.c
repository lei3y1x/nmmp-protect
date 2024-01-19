//
// Created by mao on 20-9-11.
//

#include "ConstantPool.h"

s4 binarySearch(const u4 *pool, int poolSize,
                u4 idx) {
    // Note: Signed type is important for max and min.


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

s2 quickSort(const u2 *o, int ps, u2 index) {

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
s2 qs(const u2 *o, int ps, u2 index) {

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
