package com.splichus.cleverlanceApp.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.splichus.cleverlanceApp.Utils;
import com.splichus.cleverlanceApp.presenter.ImagePresenter;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    MainActivity mainActivity;

    @Before
    public void setupTests() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void visibilityCheck() {
        assertEquals(mainActivity.progressBar.getVisibility(), View.GONE);
        assertEquals(mainActivity.imageView.getVisibility(), View.GONE);
        assertEquals(mainActivity.errorlog.getVisibility(), View.GONE);
        mainActivity.submit.performClick();
        assertEquals(mainActivity.progressBar.getVisibility(), View.VISIBLE);
    }

    @Test
    public void visibilityCheckOnPictureLoaded() {
        Bitmap bitmap = Utils.base64Decoder("poklop");
        mainActivity.onPictureLoaded(bitmap);
        assertEquals(mainActivity.imageView.getVisibility(), View.VISIBLE);
        assertEquals(mainActivity.errorlog.getVisibility(), View.GONE);
        assertEquals(mainActivity.progressBar.getVisibility(), View.GONE);
    }

    @Test
    public void visibilityCheckOnPictureLoadFail() {
        mainActivity.onPictureLoadFail("error");
        assertEquals(mainActivity.progressBar.getVisibility(), View.GONE);
        assertEquals(mainActivity.imageView.getVisibility(), View.GONE);
        assertEquals(mainActivity.errorlog.getVisibility(), View.VISIBLE);
        assertEquals(mainActivity.errorlog.getText(), "error");
    }
}
