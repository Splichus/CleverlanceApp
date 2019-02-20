package com.splichus.cleverlanceApp.model;

import android.graphics.Bitmap;

public interface ImageListener {

    void onPictureLoaded(Bitmap picture);
    void onPictureLoadFail(String string);
}
