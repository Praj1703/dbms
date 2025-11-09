#include <jni.h>
#include "ArithmeticOperations.h"

/* Implementation of native methods */

JNIEXPORT jint JNICALL Java_ArithmeticOperations_add(JNIEnv *env, jobject obj, jint a, jint b) {
    return a + b;
}

JNIEXPORT jint JNICALL Java_ArithmeticOperations_subtract(JNIEnv *env, jobject obj, jint a, jint b) {
    return a - b;
}

JNIEXPORT jint JNICALL Java_ArithmeticOperations_multiply(JNIEnv *env, jobject obj, jint a, jint b) {
    return a * b;
}

JNIEXPORT jint JNICALL Java_ArithmeticOperations_divide(JNIEnv *env, jobject obj, jint a, jint b) {
    if (b == 0) {
        // simple handling — return 0 on division by zero (you can throw exception instead)
        return 0;
    }
    return a / b;
}
