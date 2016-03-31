package com.laptop.flightstatus.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Aircraft {

    @SerializedName("registrationCode")
    @Expose
    public String registrationCode;

    public String getRegistrationCode() {
        return registrationCode;
    }
}