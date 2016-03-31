package com.laptop.flightstatus.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Leg {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("scheduledDepartureDateTime")
    @Expose
    public String scheduledDepartureDateTime;
    @SerializedName("actualDepartureDateTime")
    @Expose
    public String actualDepartureDateTime;
    @SerializedName("scheduledArrivalDateTime")
    @Expose
    public String scheduledArrivalDateTime;
    @SerializedName("estimatedArrivalDateTime")
    @Expose
    public String estimatedArrivalDateTime;

    public String getStatus() {
        return status;
    }

    public String getScheduledDepartureDateTime() {
        return scheduledDepartureDateTime;
    }

    public String getActualDepartureDateTime() {
        return actualDepartureDateTime;
    }

    public String getScheduledArrivalDateTime() {
        return scheduledArrivalDateTime;
    }

    public String getEstimatedArrivalDateTime() {
        return estimatedArrivalDateTime;
    }
}