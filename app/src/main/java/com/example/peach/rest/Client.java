package com.example.peach.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client
{
    private String baseURL;
    private Retrofit retrofit;

    public Client(String baseURL)
    {
        this.baseURL = baseURL;
    }

    public Retrofit getRetrofitClient()
    {
        if (this.retrofit == null)
        {
            this.retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
