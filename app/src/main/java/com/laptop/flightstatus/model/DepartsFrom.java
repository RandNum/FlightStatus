package com.laptop.flightstatus.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;



public class DepartsFrom {

    @SerializedName("@type")
    @Expose
    public String Type;
    @SerializedName("IATACode")
    @Expose
    public String IATACode;

    public String getType() {
        return Type;
    }

    public String getIATACode() {
        return IATACode;
    }
}