package com.borio.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.borio.ProvidersAdapter;
import com.borio.R;
import com.borio.Utils;
import com.borio.data.ProviderInfo;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class NewProviderInfoActivity extends AppCompatActivity {

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    private ProviderInfo providerInfo;
    private List<ProviderInfo> providerInfos;

    private ImageView providerImage;
    private AutoCompleteTextView providerAutocompleteText;
    private EditText usernameEditText;
    private TextView passTextView;
    private FancyButton saveButton;
    private FancyButton generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Panton-SemiBoldItalic.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_new_provider_info);
        getSupportActionBar().hide();

        initViews();
        generateRandomPassword();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        providerInfo = new ProviderInfo();
        providerInfos = getIntent().getParcelableArrayListExtra("providers_infos");
    }

    private void initViews() {
        providerImage = (ImageView) findViewById(R.id.image_provider);
        providerAutocompleteText = (AutoCompleteTextView) findViewById(R.id.et_provider);
        usernameEditText = (EditText) findViewById(R.id.et_username);
        passTextView = (TextView) findViewById(R.id.tv_password);
        saveButton = (FancyButton) findViewById(R.id.btn_save_provider);
        generateButton = (FancyButton) findViewById(R.id.btn_random_password);

        ProvidersAdapter adapter = new ProvidersAdapter(NewProviderInfoActivity.this,
                new ArrayList(Utils.providersImages.keySet()),
                new ArrayList(Utils.providersImages.values()));
        providerAutocompleteText.setAdapter(adapter);
        providerAutocompleteText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Drawable drawable;
                String providerName = s.toString().trim().toLowerCase();
                if (Utils.providersImages.containsKey(providerName)) {
                    drawable = getResources().getDrawable(Utils.providersImages.get(providerName));
                } else {
                    drawable = getResources().getDrawable(Utils.providersImages.get("provider"));
                }
                providerImage.setImageDrawable(drawable);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomPassword();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProvider();
            }
        });
    }

    private void generateRandomPassword() {
        passTextView.setText(randomString(20));
    }

    private void addNewProvider() {
        // handle add new provider info to server
        System.out.println("addNewProvider");
        providerInfo.setProvider(providerAutocompleteText.getText().toString());
        providerInfo.setUsername(usernameEditText.getText().toString());
        providerInfo.setPassword(passTextView.getText().toString());
        providerInfos.add(providerInfo);
        Intent intent = new Intent(NewProviderInfoActivity.this, MainActivity.class);
        intent.putParcelableArrayListExtra("providers_infos", new ArrayList(providerInfos));
        startActivity(intent);
    }

    private String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
