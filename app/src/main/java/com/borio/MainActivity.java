package com.borio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements RequestTask.AsyncRequestResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestTask request = new RequestTask(this);
        request.execute("/sample");
    }

    @Override
    public void onFetchingRequestFinish(Borio result) {
        System.out.println(result.getUsername());
        System.out.println(result.getPasswords().size());
        for (ProviderPassword pass : result.getPasswords()) {
            System.out.print(pass.getProvider());
            System.out.println(": " + pass.getPassword());
        }
    }

}
