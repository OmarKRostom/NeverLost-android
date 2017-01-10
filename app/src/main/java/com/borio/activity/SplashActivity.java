package com.borio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.borio.AesUtil;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

//        String iv = AesUtil.random(16);
//        String salt = AesUtil.random(16);
//        String plainText = "Ahmed";
//        String passphrase = "emad";
//        System.out.println("iv: " + iv);
//        System.out.println("salt: " + salt);
//
//        AesUtil aesUtil = new AesUtil();
//        // Encrypt
//        String cipherText = aesUtil.encrypt(salt, iv, passphrase, plainText);
//        System.out.println(cipherText);
//        // Decrypt
//        String plainText2 = aesUtil.decrypt(salt, iv, passphrase, cipherText);
//        System.out.println(plainText2);
    }

}
