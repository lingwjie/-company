#include<jni.h>
#include <stdio.h>
#include "com_gwj_jnijava_MainActivity.h"
JNIEXPORT jint JNICALL Java_com_gwj_jnijava_MainActivity_calladd
(JNIEnv *env, jobject obj , jint a , jint b)
{
	jint c ;
	jclass stringClass = (*env)->FindClass(env, "com/gwj/jnijava/MainActivity");
	 if (stringClass == NULL) {
		 return NULL;
	 }
	jmethodID cid = (*env)->GetMethodID(env, stringClass,
							   "add", "(II)I");
	 if (cid == NULL) {
		 return NULL;
	 }
	 //jint (JNICALL *CallIntMethod)(JNIEnv* pEnv, jobject obj, jmethodID methodID, ...);
	 c = (*env)->CallIntMethod(env ,obj,cid,a,b);
}
