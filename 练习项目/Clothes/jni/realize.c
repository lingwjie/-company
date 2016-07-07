#include<jni.h>
#include <stdio.h>
#include "com_gwj_clothes_analysisrealize.h"
int rightvalue(int a)
{
	int i ;
	if(a < 0)
	{
		int j;
		j = a & 0x7;
		i = j+8;
	}
	else
		i = a;
	return i;
}

int addrtoint(char* a , int flag)
{
	int sum = 0;
	int i = 0;
	if(flag == 1)
	{
		sum += rightvalue((a[3]>>4)) *16*16*16*16*16*16*16;
		sum += rightvalue((((a[3]<<4) & 0xf0)>>4)) *16*16*16*16*16*16;
		sum += rightvalue((a[2]>>4)) *16*16*16*16*16;
		sum += rightvalue((((a[2]<<4) & 0xf0)>>4)) *16*16*16*16;
		sum += rightvalue((a[1]>>4)) *16*16*16;
		sum += rightvalue((((a[1]<<4) & 0xf0)>>4)) *16*16;
		sum += rightvalue((a[0]>>4)) *16;
		sum += rightvalue((((a[0]<<4) & 0xf0)>>4)) *1;
	}
	else
	{
		sum += rightvalue((a[0]>>4)) *16*16*16*16*16*16*16;
		sum += rightvalue((((a[0]<<4) & 0xf0)>>4)) *16*16*16*16*16*16;
		sum += rightvalue((a[1]>>4)) *16*16*16*16*16;
		sum += rightvalue((((a[1]<<4) & 0xf0)>>4)) *16*16*16*16;
		sum += rightvalue((a[2]>>4)) *16*16*16;
		sum += rightvalue((((a[2]<<4) & 0xf0)>>4)) *16*16;
		sum += rightvalue((a[3]>>4)) *16;
		sum += rightvalue((((a[3]<<4) & 0xf0)>>4)) *1;
	}
	return sum;
}
JNIEXPORT jint JNICALL Java_com_gwj_clothes_analysisrealize_getinitaladdress
  (JNIEnv * env, jobject obj, jstring path)
{
	char *str=(*env)->GetStringUTFChars(env,path,0);
	FILE *in = fopen(str,"r");

	jint a;
	char versionifo[4];
	char data[4];
	char projectnameaddr[4];
	char datatableaddr[4];

	fseek(in,256,SEEK_SET);
	fread(versionifo, 4, 1, in);
	fread(data, 4, 1, in);
	fread(projectnameaddr, 4, 1, in);
	fread(datatableaddr, 4, 1, in);
	a = addrtoint(datatableaddr,1);
	fclose(in);
	return (jint)a;
}

/*JNIEXPORT void JNICALL Java_com_gwj_clothes_analysisrealize_getinitaladdress
  (JNIEnv * env, jobject obj, jstring path)
{
	jint c ;
	//jbyte buf[][][][];
	//jbyteArray byteArr = (*env)->NewByteArray(env, strlen(buf));
	jclass stringClass = (*env)->FindClass(env, "com/gwj/clothes/analysisrealize");
	 if (stringClass == NULL) {
		 return NULL;
	 }
	jmethodID cid = (*env)->GetMethodID(env, stringClass,
							   "getimage", "(Ljava/lang/String;)[[[[B");
	 if (cid == NULL) {
		 return NULL;
	 }
	 //void (JNICALL *CallVoidMethod)(JNIEnv* pEnv, jobject obj, jmethodID methodID, ...);
	 //jbyte (JNICALL *CallByteMethod)(JNIEnv* pEnv, jobject obj, jmethodID methodID, ...);
	 //void (JNICALL *SetByteArrayRegion)(JNIEnv* pEnv, jbyteArray array, jsize start, jsize len, jbyte *buf);
	 //jobjectArray (JNICALL *NewObjectArray)(JNIEnv* pEnv, jsize len, jclass clazz, jobject init);
	//jobject (JNICALL *CallObjectMethod)(JNIEnv* pEnv, jobject obj, jmethodID methodID, ...);
	 //jobject object = CallObjectMethod(...);
	 //jIntArray result = (jIntArray)object;

	 //jbyte *buf= (*env)->CallByteMethod(env ,obj , cid , path);
	 //int bytekeylen = (*env)->GetArrayLength(env,buf);
	 jobject object= (*env)->CallObjectMethod(env ,obj , cid , path);
	 if (object == NULL) {
	 		 return NULL;
	 	 }
	 jobjectArray result = (jobjectArray)object;
	 //(*env)->SetByteArrayRegion(env,byteArr, 0,bytekeylen,buf);
}*/
