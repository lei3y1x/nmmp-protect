package com.nmmedit.apkprotect.data.config;

import com.google.gson.annotations.SerializedName;

public class PathConfig {
    @SerializedName("sdk_path")
    public String sdk_path;
    @SerializedName("cmake_path")
    public String cmake_path;
    @SerializedName("ndk_path")
    public String ndk_path;

    @SerializedName("ndk_toolchains")
    public String ndk_toolchains;
    @SerializedName("ndk_abi")
    public String ndk_abi;
    @SerializedName("ndk_strip")
    public String ndk_strip;
    @SerializedName("so_name")
    public String so_name;
    @SerializedName("so_init_calss")
    public String so_init_calss;
    @SerializedName("so_init_method")
    public String so_init_method;
    @SerializedName("methodType")
    public String methodType;
    @SerializedName("init_metho")
    public String init_metho;
}
