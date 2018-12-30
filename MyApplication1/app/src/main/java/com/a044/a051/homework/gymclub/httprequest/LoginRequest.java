package com.a044.a051.homework.gymclub.httprequest;

import com.a044.a051.homework.gymclub.Translation;

import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginRequest {
    @POST("login")
    @FormUrlEncoded
    Call<LoginResult> login(@Field("username") String username,@Field("password") String password);
}
