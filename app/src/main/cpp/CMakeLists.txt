# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)

# Declares and names the project.

project("fastddsserver")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        fastddsserver

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        native-lib.cpp
        test.cpp
        # fast-dds helloworld
        HelloWorld.cxx
        HelloWorld.h
        HelloWorld.idl
        HelloWorldPublisher.cpp
        HelloWorldPubSubTypes.cxx
        HelloWorldPubSubTypes.h
        HelloWorldSubscriber.cpp
        )

# 添加第三方库
add_library(
        fastcdr
        SHARED
        IMPORTED)

## 配置第三方库链接
set_target_properties(
        fastcdr
        PROPERTIES
        IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/libs/libfastcdr.so)

add_library(
        fastrtps
        SHARED
        IMPORTED)

## 配置第三方库链接
set_target_properties(
        fastrtps
        PROPERTIES
        IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/libs/libfastrtps.so)

## 添加第三方库
add_library(
        foonathan
        SHARED
        IMPORTED)

# 配置第三方库链接
set_target_properties(
        foonathan
        PROPERTIES
        IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/libs/libfoonathan_memory-0.7.1.so)


# 添加头文件
include_directories(include)

#include_directories(ddsserver/src)

# set output dir
#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/jniLibs/${ANDROID_ABI})



# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.


target_link_libraries( # Specifies the target library.
        fastddsserver
        fastcdr
        fastrtps
        foonathan
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib}
        )