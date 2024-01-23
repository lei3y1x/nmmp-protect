cmake_minimum_required(VERSION 3.6)
project(nmmp)


add_subdirectory(vm)


include_directories(.)
include_directories(generated/)


file(GLOB GEN_SOURCES generated/*_functions.c generated/jni_init.c)


#set(CMAKE_C_FLAGS_RELEASE "${CMAKE_C_FLAGS_RELEASE} -fvisibility=hidden ")
#set(CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} -fvisibility=hidden ")


#cppFlags "-std=c++14 -fexceptions -fvisibility=hidden"
#cppFlags "-mllvm -fla -mllvm -split -mllvm -split_num=3 -mllvm -sub -mllvm -sub_loop=3 -mllvm -sobf"
#4
#set(CMAKE_C_FLAGS_RELEASE "-fvisibility=hidden -mllvm -fla -mllvm -split -mllvm -split_num=4 -mllvm -sub -mllvm -sub_loop=4 -mllvm -sobf -mllvm -bcf_loop=4")
#set(CMAKE_CXX_FLAGS "-fvisibility=hidden -mllvm -fla -mllvm -split -mllvm -split_num=4 -mllvm -sub -mllvm -sub_loop=4 -mllvm -sobf -mllvm -bcf_loop=4")

set(CMAKE_C_FLAGS_RELEASE "-fvisibility=hidden -mllvm -fla -mllvm -split -mllvm -split_num=5 -mllvm -sub -mllvm -sub_loop=5 -mllvm -sobf -mllvm -bcf_loop=5")
set(CMAKE_CXX_FLAGS "-fvisibility=hidden -mllvm -fla -mllvm -split -mllvm -split_num=5 -mllvm -sub -mllvm -sub_loop=5 -mllvm -sobf -mllvm -bcf_loop=5")

#set(CMAKE_C_FLAGS_RELEASE "-fvisibility=hidden -mllvm -sub -mllvm -sobf -mllvm -fla -mllvm -bcf_loop=2")
#set(CMAKE_CXX_FLAGS "-fvisibility=hidden -mllvm -sub -mllvm -sobf -mllvm -fla -mllvm -bcf_loop=2")

#外部程序处理时替换 LIBNAME_PLACEHOLDER
set(LIBNAME_PLACEHOLDER "nmmp")


add_library(${LIBNAME_PLACEHOLDER} SHARED ConstantPool.c ${GEN_SOURCES} )

target_link_libraries(${LIBNAME_PLACEHOLDER} ${LIBNMMVM_NAME} log)

