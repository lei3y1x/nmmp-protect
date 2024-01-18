package com.nmmedit.apkprotect.data.config;

import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("abi")
    public AbiConfig abi;
    @SerializedName("environment")
    public PathConfig environment;
    @SerializedName("abiconfig")
    public PathConfig abiconfig;

    public Config(AbiConfig abi, PathConfig environment,PathConfig nativeConfig) {
        this.abi = abi;
        this.environment = environment;
        this.abiconfig = nativeConfig;
    }
}