package com.borio.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.borio.R;
import com.borio.Utils;
import com.borio.data.ProviderInfo;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Ahmed Emad on 4 May, 2015.
 */

public class ProviderInfoActivity extends AppCompatActivity {

    ImageView mImage;
    TextView mProvider;
    TextView mUsername;
    TextView mPassword;
    FancyButton mNewPassword;

    private ProviderInfo providerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Panton-SemiBoldItalic.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_provider_info);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        providerInfo = getIntent().getParcelableExtra("provider_info");

        initViews();

        setValues();
    }

    private void initViews() {
        mImage = (ImageView) findViewById(R.id.image_provider);
        mProvider = (TextView) findViewById(R.id.tv_provider);
        mUsername = (TextView) findViewById(R.id.tv_username);
        mPassword = (TextView) findViewById(R.id.tv_password);
        mNewPassword = (FancyButton) findViewById(R.id.btn_new_password);
    }

    private void setValues() {
        mProvider.setText(providerInfo.getProvider());
        mUsername.setText(providerInfo.getUsername());
        mPassword.setText(providerInfo.getPassword());

        Drawable drawable;
        String providerName = providerInfo.getProvider().trim().toLowerCase();
        if (Utils.providersImages.containsKey(providerName)) {
            drawable = getResources().getDrawable(Utils.providersImages.get(providerName));
        } else {
            drawable = getResources().getDrawable(Utils.providersImages.get("provider"));
        }
        mImage.setImageDrawable(drawable);

        mNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateNewPassword();
            }
        });
    }

    void generateNewPassword() {
        System.out.println("generateNewPassword");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
