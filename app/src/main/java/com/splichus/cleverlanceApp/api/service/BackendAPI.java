package com.splichus.cleverlanceApp.api.service;

import com.splichus.cleverlanceApp.api.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BackendAPI {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("download/bootcamp/image.php")
    Call<ApiResponse> getImage (@Header("authorization") String encryptedPassword, @Field("username") String username);

}
