//
// Created by mao on 20-9-11.
//

#include "ScopedLocalRef.h"
#include "GlobalCache.h"
#include "vm.h"

#ifdef __cplusplus
extern "C" {
#endif
//基本类型class
static jclass primitiveBooleanClass;
static jclass primitiveByteClass;
static jclass primitiveCharClass;
static jclass primitiveShortClass;
static jclass primitiveIntClass;
static jclass primitiveFloatClass;
static jclass primitiveLongClass;
static jclass primitiveDoubleClass;

//得到8个基本类型的class
static jobject getPrimitiveTypeClass(JNIEnv *env, const char *classname) {
    ScopedLocalRef<jclass> clazz(env, env->FindClass(classname));
    jfieldID fieldId = env->GetStaticFieldID(clazz.get(), "TYPE", "Ljava/lang/Class;");
    return env->GetStaticObjectField(clazz.get(), fieldId);
}

//全局异常class
__attribute__((visibility("default")))
vmGlobals ggg;

//缓存一些常用的class对象,比如基本类型class对象
__attribute__((visibility("default")))
void cInital(JNIEnv *env) {
    {
        ScopedLocalRef<jobject> obj(env, getPrimitiveTypeClass(env, "java/lang/Boolean"));
        primitiveBooleanClass = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, getPrimitiveTypeClass(env, "java/lang/Byte"));
        primitiveByteClass = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, getPrimitiveTypeClass(env, "java/lang/Character"));
        primitiveCharClass = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, getPrimitiveTypeClass(env, "java/lang/Short"));
        primitiveShortClass = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, getPrimitiveTypeClass(env, "java/lang/Integer"));
        primitiveIntClass = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, getPrimitiveTypeClass(env, "java/lang/Float"));
        primitiveFloatClass = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, getPrimitiveTypeClass(env, "java/lang/Long"));
        primitiveLongClass = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, getPrimitiveTypeClass(env, "java/lang/Double"));
        primitiveDoubleClass = (jclass) env->NewGlobalRef(obj.get());
    }

    //取得且缓存全局异常class对象
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NoClassDefFoundError"));
        ggg.exNoClassDefFoundError = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NoSuchFieldError"));
        ggg.exNoSuchFieldError = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NoSuchFieldException"));
        ggg.exNoSuchFieldException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NoSuchMethodError"));
        ggg.exNoSuchMethodError = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NullPointerException"));
        ggg.exNullPointerException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/ArithmeticException"));
        ggg.exArithmeticException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/InternalError"));
        ggg.exInternalError = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NegativeArraySizeException"));
        ggg.exNegativeArraySizeException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env,
                                    env->FindClass("java/lang/ArrayIndexOutOfBoundsException"));
        ggg.exArrayIndexOutOfBoundsException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/ClassCastException"));
        ggg.exClassCastException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/ClassNotFoundException"));
        ggg.exClassNotFoundException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/RuntimeException"));
        ggg.exRuntimeException = (jclass) env->NewGlobalRef(obj.get());
    }
}

__attribute__((visibility("default")))
jclass getCC(JNIEnv *env, const char *type) {
    switch (*type) {
        case 'Z':
            return primitiveBooleanClass;
        case 'B':
            return primitiveByteClass;
        case 'C':
            return primitiveCharClass;
        case 'S':
            return primitiveShortClass;
        case 'I':
            return primitiveIntClass;
        case 'F':
            return primitiveFloatClass;
        case 'J':
            return primitiveLongClass;
        case 'D':
            return primitiveDoubleClass;
        default:
            return NULL;
    }
}


#ifdef __cplusplus
}
#endif

