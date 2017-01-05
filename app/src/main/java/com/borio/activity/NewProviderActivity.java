package com.borio.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.borio.R;
import com.borio.data.ProviderInfo;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class NewProviderActivity extends AppCompatActivity {

    private ProviderInfo providerInfo;
    private List<ProviderInfo> providerInfos;


    private EditText prEditText;
    private EditText uEditText;
    private EditText pEditText;
    private FancyButton aFancyButton;
    private FancyButton gFancyButton;

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Panton-SemiBoldItalic.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_new_provider);
//        getSupportActionBar().hide();
        initViews();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        providerInfo = new ProviderInfo();
        providerInfos = getIntent().getParcelableArrayListExtra("providers_infos");
        System.out.println(providerInfo);
    }

    private void initViews() {
        prEditText = (EditText) findViewById(R.id.et_provider);
        uEditText = (EditText) findViewById(R.id.et_username);
        pEditText = (EditText) findViewById(R.id.et_password);
        aFancyButton = (FancyButton) findViewById(R.id.btn_save_provider);
        gFancyButton = (FancyButton) findViewById(R.id.btn_random_passowrd);
        gFancyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomPassword();
            }
        });
        aFancyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProvider();
            }
        });

    }

    private void generateRandomPassword() {
        pEditText.setText(randomString(20));
    }

    private void addNewProvider() {
        // handle addnewprovider to server
        System.out.println("addNewProvider");
        providerInfo.setProvider(prEditText.getText().toString());
        providerInfo.setUsername(uEditText.getText().toString());
        providerInfo.setPassword(pEditText.getText().toString());
        providerInfos.add(providerInfo);
        Intent intent = new Intent(NewProviderActivity.this, MainActivity.class);
        intent.putParcelableArrayListExtra("providers_infos", new ArrayList(providerInfos));
        startActivity(intent);
    }

    private String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }


}
