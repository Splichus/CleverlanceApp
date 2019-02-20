package com.splichus.cleverlanceApp.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.splichus.cleverlanceApp.Constants;
import com.splichus.cleverlanceApp.api.model.ApiResponse;
import com.splichus.cleverlanceApp.api.service.BackendAPI;

import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageService {

    private ImageListener activity;
    private BackendAPI api;

    public ImageService(BackendAPI api) {
        this.api = api;
    }

    public void setActivity (ImageListener activity) {
        this.activity = activity;
    }

    public void giveImage(String username, String password) throws NoSuchAlgorithmException {
        String hashedPass = HashGenerator.sha1(password);
        api.getImage(hashedPass, username).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() == 200 && response.body() != null){
                    byte[] decodedString = Base64.decode(response.body().getImage(), Base64.DEFAULT);
                    Bitmap picture = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    activity.onPictureLoaded(picture);
                } else if (response.code() == 401){
                    activity.onPictureLoadFail(Constants.UNAUTHORIZED);
                } else {
                    activity.onPictureLoadFail(Constants.CONNECTION);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                activity.onPictureLoadFail(Constants.CONNECTION);
            }
        });
    }


}
