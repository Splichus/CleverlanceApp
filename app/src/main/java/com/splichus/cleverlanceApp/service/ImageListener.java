package com.splichus.cleverlanceApp.service;

import android.graphics.Bitmap;

public interface ImageListener {

    void onPictureLoaded(Bitmap picture);
    void onPictureLoadFail(String string);
}
