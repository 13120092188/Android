package com.a044.a051.homework.gymclub.httprequest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterRequest {
    @POST("register")
    @FormUrlEncoded
    Call<RegisterResult> register(@Field("username") String username, @Field("password") String password);
}
