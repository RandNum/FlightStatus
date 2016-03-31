package com.laptop.flightstatus.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Carrier {

    @SerializedName("code")
    @Expose
    public String code;

    public String getCode() {
        return code;
    }
}