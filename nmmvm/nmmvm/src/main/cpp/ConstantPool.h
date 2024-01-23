//
// Created by mao on 20-9-11.
//

#ifndef DEX_EDITOR_CONSTANTPOOL_H
#define DEX_EDITOR_CONSTANTPOOL_H

#include "Common.h"
#include <jni.h>


s4 binarySearch(const u4 *pool, int poolSize,
                u4 idx);
s2 binarySort (const u2 *o,int  ps,u2 index);
s2 binaryFind (const u2 *o,int  ps,u2 index);
s2 binaryRegister();
s2 binaryVMFind();
s4 binaryvm();
s4 binarydvm();

#endif //DEX_EDITOR_CONSTANTPOOL_H
