package com.laptop.flightstatus.services;

//import com.squareup.okhttp.OkHttpClient;



import okhttp3.Interceptor;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


public class ServiceFactory {

   public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {

       HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
       logging.setLevel(HttpLoggingInterceptor.Level.BODY);
       okhttp3.OkHttpClient mclient = new okhttp3.OkHttpClient.Builder().addInterceptor(logging).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mclient)
                .build();
        T service = retrofit.create(clazz);

        return service;
    }


}
