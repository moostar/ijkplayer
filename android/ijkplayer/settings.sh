#!/bin/bash

# set the base path to your Android NDK (or export NDK to environment)

ANDROID_NDK=/c/Android/NDK/android-ndk-r9d
if [[ "x$ANDROID_NDK" == "x" ]]; then
    NDK_BUILD_PATH=$(which ndk-build)
		ANDROID_NDK=$(dirname $NDK_BUILD_PATH)
    echo "No ANDROID_NDK set, using $ANDROID_NDK"
fi


NDK_PLATFORM_VERSION=8
NDK_SYSROOT=$ANDROID_NDK/platforms/android-$NDK_PLATFORM_VERSION/arch-arm
NDK_UNAME=`uname -s | tr '[A-Z]' '[a-z]'`
NDK_TOOLCHAIN_BASE=$ANDROID_NDK/toolchains/arm-linux-androideabi-4.8/prebuilt/windows-x86_64
CC="$NDK_TOOLCHAIN_BASE/bin/arm-linux-androideabi-gcc --sysroot=$NDK_SYSROOT"
LD=$NDK_TOOLCHAIN_BASE/bin/arm-linux-androideabi-ld

# i use only a small number of formats - set this to 0 if you want everything.
# changed 0 to the default, so it'll compile shitloads of codecs normally
if [[ "x$minimal_featureset" == "x" ]]; then
minimal_featureset=1
fi

function current_dir {
  echo "$(cd "$(dirname $0)"; pwd)"
}

