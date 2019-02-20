package com.splichus.cleverlanceApp.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.splichus.cleverlanceApp.R;
import com.splichus.cleverlanceApp.model.ImageListener;
import com.splichus.cleverlanceApp.presenter.ImagePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements ImageListener {

    @Inject
    ImagePresenter imagePresenter;

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
    @BindView(R.id.main_error_message)
    TextView errorlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        imagePresenter.setActivity(this);

    }

    @OnClick(R.id.main_button_submit)
    public void login() {
        imageView.setVisibility(View.GONE);
        errorlog.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        imagePresenter.giveImage(username.getText().toString(), password.getText().toString());

    }

    @Override
    public void onPictureLoaded(Bitmap picture) {
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(picture);
    }

    @Override
    public void onPictureLoadFail(String error) {
        progressBar.setVisibility(View.GONE);
        errorlog.setVisibility(View.VISIBLE);
        errorlog.setText(error);
    }
}
