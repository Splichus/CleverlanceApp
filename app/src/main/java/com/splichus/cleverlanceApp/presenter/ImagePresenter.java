package com.splichus.cleverlanceApp.presenter;

import com.splichus.cleverlanceApp.Constants;
import com.splichus.cleverlanceApp.Utils;
import com.splichus.cleverlanceApp.api.model.ApiResponse;
import com.splichus.cleverlanceApp.api.service.BackendAPI;
import com.splichus.cleverlanceApp.model.ImageListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagePresenter {

    private ImageListener activity;
    private BackendAPI api;

    public ImagePresenter(BackendAPI api) {
        this.api = api;
    }

    public void setActivity (ImageListener activity) {
        this.activity = activity;
    }

    public void giveImage(String username, String password) {
        String hashedPass = Utils.sha1Encoder(password);
        api.getImage(hashedPass, username).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() == 200 && response.body() != null){
                    activity.onPictureLoaded(Utils.base64Decoder(response.body().getImage()));
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
