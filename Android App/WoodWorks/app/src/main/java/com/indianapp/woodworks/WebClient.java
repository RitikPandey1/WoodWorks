package com.indianapp.woodworks;

import com.google.gson.JsonObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebClient {
    @POST("/user/register")
    Call<JsonObject> executeSignup (
            @Body HashMap<String, String> map
    );
    @POST("/user/login")
    Call<JsonObject> executeLogin (
            @Body HashMap<String, String> map
    );
}
