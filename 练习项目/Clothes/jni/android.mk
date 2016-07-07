LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := calljni
LOCAL_SRC_FILES := realize.c
include $(BUILD_SHARED_LIBRARY)
