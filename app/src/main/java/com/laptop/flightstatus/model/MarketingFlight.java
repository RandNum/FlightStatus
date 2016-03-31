package com.laptop.flightstatus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MarketingFlight {

    @SerializedName("flightNumber")
    @Expose
    public String flightNumber;

    public String getFlightNumber() {
        return flightNumber;
    }
}