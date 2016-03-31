package com.laptop.flightstatus.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OperatingFlightLeg {

    @SerializedName("departsFrom")
    @Expose
    public DepartsFrom departsFrom;
    @SerializedName("arrivesOn")
    @Expose
    public ArrivesOn arrivesOn;
    @SerializedName("scheduledDepartureDateTime")
    @Expose
    public String scheduledDepartureDateTime;
    @SerializedName("scheduledArrivalDateTime")
    @Expose
    public String scheduledArrivalDateTime;
    @SerializedName("flightStatus")
    @Expose
    public String flightStatus;
    @SerializedName("legs")
    @Expose
    public List<Leg> legs = new ArrayList<>();

    public DepartsFrom getDepartsFrom() {
        return departsFrom;
    }

    public ArrivesOn getArrivesOn() {
        return arrivesOn;
    }

    public String getScheduledDepartureDateTime() {
        return scheduledDepartureDateTime;
    }

    public String getScheduledArrivalDateTime() {
        return scheduledArrivalDateTime;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public List<Leg> getLegs() {
        return legs;
    }
}