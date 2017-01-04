package com.borio.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.borio.R;
import com.borio.view.FormView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String VIDEO_NAME = "welcome_video.mp4";

    private VideoView mVideoView;
    private Button buttonLogin;
    private Button buttonSignup;
    private FormView formView;
    private TextView tvAppName;

    private InputType inputType = InputType.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initView();

        File videoFile = getFileStreamPath(VIDEO_NAME);
        if (!videoFile.exists()) {
            videoFile = copyVideoFile();
        }

        playVideo(videoFile);
        playAnim();
    }

    private void initView() {
        mVideoView = (VideoView) findViewById(R.id.video_view);
        buttonSignup = (Button) findViewById(R.id.button_signup);
        buttonLogin = (Button) findViewById(R.id.button_login);
        formView = (FormView) findViewById(R.id.form_view);
        tvAppName = (TextView) findViewById(R.id.tv_app_name);
        formView.post(new Runnable() {
            @Override
            public void run() {
                int delta = formView.getTop() + formView.getHeight();
                formView.setTranslationY(-1 * delta);
            }
        });
        buttonSignup.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
    }

    private void playVideo(File videoFile) {
        mVideoView.setVideoPath(videoFile.getPath());
        mVideoView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        });
    }

    private void playAnim() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(tvAppName, "alpha", 0, 1);
        anim.setDuration(4000);
        anim.setRepeatCount(1);
        anim.setRepeatMode(ObjectAnimator.REVERSE);
        anim.start();
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                tvAppName.setVisibility(View.INVISIBLE);
            }
        });
    }

    @NonNull
    private File copyVideoFile() {
        File videoFile;
        try {
            FileOutputStream fos = openFileOutput(VIDEO_NAME, MODE_PRIVATE);
            InputStream in = getResources().openRawResource(R.raw.welcome_video);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoFile = getFileStreamPath(VIDEO_NAME);
        if (!videoFile.exists()) {
            throw new RuntimeException("Video file has a problem. Check welcome_video.mp4.");
        }
        return videoFile;
    }

    @Override
    public void onBackPressed() {
        if (inputType != InputType.NONE) {
            int delta = formView.getTop() + formView.getHeight();
            formView.animate().translationY(-1 * delta).alpha(0).setDuration(500).start();
            inputType = InputType.NONE;
            buttonLogin.setText(R.string.button_login);
            buttonSignup.setText(R.string.button_signup);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }

    @Override
    public void onClick(View view) {
        int delta = formView.getTop() + formView.getHeight();
        switch (inputType) {
            case NONE:
                formView.animate().translationY(0).alpha(1).setDuration(500).start();
                if (view == buttonLogin) {
                    inputType = InputType.LOGIN;
                    buttonLogin.setText(R.string.button_confirm_login);
                    buttonSignup.setText(R.string.button_cancel_login);
                } else if (view == buttonSignup) {
                    inputType = InputType.SIGN_UP;
                    buttonLogin.setText(R.string.button_confirm_signup);
                    buttonSignup.setText(R.string.button_cancel_signup);
                }
                break;
            case LOGIN:
                formView.animate().translationY(-1 * delta).alpha(0).setDuration(500).start();
                if (view == buttonLogin) {
                    System.out.println("Login");
                } else if (view == buttonSignup) {
                    System.out.println("Cancel Login");
                }
                inputType = InputType.NONE;
                buttonLogin.setText(R.string.button_login);
                buttonSignup.setText(R.string.button_signup);
                break;
            case SIGN_UP:
                formView.animate().translationY(-1 * delta).alpha(0).setDuration(500).start();
                if (view == buttonLogin) {
                    System.out.println("Signup");
                } else if (view == buttonSignup) {
                    System.out.println("Cancel Signup");
                }
                inputType = InputType.NONE;
                buttonLogin.setText(R.string.button_login);
                buttonSignup.setText(R.string.button_signup);
                break;
        }
    }

    enum InputType {
        NONE, LOGIN, SIGN_UP;
    }

}