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

//基本参数类型
//static jclass primitivevoid;
//static jclass primitiveBool;
//static jclass primitiveInt;
//static jclass primitiveLong;
//static jclass primitiveDouble;
//static jclass primitiveFloat;
//static jclass primitiveByte;
//static jclass primitiveChar;
//static jclass primitiveShort;
//得到8个基本类型的class
static jobject getPrimitiveTypeClass(JNIEnv *env, const char *classname) {
    ScopedLocalRef<jclass> clazz(env, env->FindClass(classname));
    jfieldID fieldId = env->GetStaticFieldID(clazz.get(), "TYPE", "Ljava/lang/Class;");
    return env->GetStaticObjectField(clazz.get(), fieldId);
}

//全局异常class
__attribute__((visibility("default")))
vmGlobals campr;

//缓存一些常用的class对象,比如基本类型class对象
__attribute__((visibility("default")))
void gcs(JNIEnv *env) {
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
        campr.exNoClassDefFoundError = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NoSuchFieldError"));
        campr.exNoSuchFieldError = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NoSuchFieldException"));
        campr.exNoSuchFieldException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NoSuchMethodError"));
        campr.exNoSuchMethodError = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NullPointerException"));
        campr.exNullPointerException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/ArithmeticException"));
        campr.exArithmeticException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/InternalError"));
        campr.exInternalError = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/NegativeArraySizeException"));
        campr.exNegativeArraySizeException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env,
                                    env->FindClass("java/lang/ArrayIndexOutOfBoundsException"));
        campr.exArrayIndexOutOfBoundsException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/ClassCastException"));
        campr.exClassCastException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/ClassNotFoundException"));
        campr.exClassNotFoundException = (jclass) env->NewGlobalRef(obj.get());
    }
    {
        ScopedLocalRef<jobject> obj(env, env->FindClass("java/lang/RuntimeException"));
        campr.exRuntimeException = (jclass) env->NewGlobalRef(obj.get());
    }
}

__attribute__((visibility("default")))
jclass getCam(JNIEnv *env, const char *type) {
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


void getType(JNIEnv *env, const char *type){

    switch (*type) {
        case 'V':

            break;

        case 'Z':

            break;

        case 'I':
            break;
        case 'J':

            break;
        case 'D':
            break;

        case 'F':
            break;

        case 'B':
            break;

        case 'C':
            break;

        case 'S':
            break;

        default:
            break;
    }

}

#ifdef __cplusplus
}
#endif

