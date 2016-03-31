package com.laptop.flightstatus.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Flight {

    @SerializedName("carrier")
    @Expose
    public Carrier carrier;
    @SerializedName("flightNumber")
    @Expose
    public String flightNumber;
    @SerializedName("operatingFlightLeg")
    @Expose
    public OperatingFlightLeg operatingFlightLeg;
    @SerializedName("aircraft")
    @Expose
    public Aircraft aircraft;
    @SerializedName("marketingFlights")
    @Expose
    public List<MarketingFlight> marketingFlights = new ArrayList<MarketingFlight>();
    @SerializedName("remainingFlyTime")
    @Expose
    public String remainingFlyTime;
    @SerializedName("@type")
    @Expose
    public String Type;

    public Carrier getCarrier() {
        return carrier;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public OperatingFlightLeg getOperatingFlightLeg() {
        return operatingFlightLeg;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public List<MarketingFlight> getMarketingFlights() {
        return marketingFlights;
    }

    public String getRemainingFlyTime() {
        return remainingFlyTime;
    }

    public String getType() {
        return Type;
    }
}