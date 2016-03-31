package com.laptop.flightstatus.services;

import com.laptop.flightstatus.model.RootResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface FoxAPIService {

    String SERVICE_ENDPOINT = "https://fox.klm.com";

    @GET("/fox/json/flightstatuses?")
    Observable<RootResponse> getStatus(@Query("departureDate") String departureDate,
                                 @Query("flightNumber") String flightNumber);

}

