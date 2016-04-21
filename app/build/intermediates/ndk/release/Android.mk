LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := libjni_pinyinime
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_LDLIBS := \
	-llog \

LOCAL_SRC_FILES := \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/Android.mk \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/command/pinyinime_dictbuilder.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/command/Makefile \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/spellingtrie.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/dictlist.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/spellingtable.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/lpicache.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/mystdlib.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/utf16reader.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/dicttrie.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/searchutility.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/matrixsearch.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/utf16char.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/dictbuilder.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/sync.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/userdict.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/splparser.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/ngram.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/share/pinyinime.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/android/com_android_inputmethod_pinyin_PinyinDecoderService.cpp \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/data/valid_utf16.txt \
	/home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni/data/rawdict_utf16_65105_freq.txt \

LOCAL_C_INCLUDES += /home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/main/jni
LOCAL_C_INCLUDES += /home/slin/AndroidStudioProjects/YounianKeyboard-Android/app/src/release/jni

include $(BUILD_SHARED_LIBRARY)
