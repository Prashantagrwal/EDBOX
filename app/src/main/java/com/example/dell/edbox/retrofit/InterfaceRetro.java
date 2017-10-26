package com.example.dell.edbox.retrofit;

import com.example.dell.edbox.pojo.JsonClass;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by DELL on 25/10/2017.
 */

public interface InterfaceRetro {
    @GET("all.json")
    Call<JsonClass> getData();
}
