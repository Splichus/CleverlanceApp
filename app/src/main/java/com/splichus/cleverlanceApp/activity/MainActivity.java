package com.splichus.cleverlanceApp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.splichus.cleverlanceApp.R;
import com.splichus.cleverlanceApp.service.ImageListener;
import com.splichus.cleverlanceApp.service.ImageService;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements ImageListener {

    @Inject
    ImageService imageService;

    @BindView(R.id.main_edittext_username)
    EditText username;
    @BindView(R.id.main_edittext_password)
    EditText password;
    @BindView(R.id.main_button_submit)
    Button submit;
    @BindView(R.id.main_image)
    ImageView imageView;
    @BindView(R.id.main_progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        imageService.setActivity(this);

    }

    @OnClick(R.id.main_button_submit)
    public void login() {
        progressBar.setVisibility(View.VISIBLE);
        try {
            imageService.giveImage(username.getText().toString(), password.getText().toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPictureLoaded(Bitmap picture) {
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(picture);
    }

    @Override
    public void onPictureLoadFail(String error) {

    }
}
