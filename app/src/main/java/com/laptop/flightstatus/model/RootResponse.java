package com.laptop.flightstatus.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RootResponse {

    @SerializedName("flights")
    @Expose
    public List<Flight> flights = new ArrayList<>();

    public List<Flight> getFlights() {
        return flights;
    }
}