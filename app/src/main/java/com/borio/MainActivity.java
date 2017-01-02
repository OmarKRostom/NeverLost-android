package com.borio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.logansquare.LoganSquare;
import com.borio.data.Borio;
import com.borio.data.ProviderPassword;
import com.borio.task.RequestTask;
import com.borio.task.UpdateTask;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements RequestTask.RequestResponse, UpdateTask.UpdateResponse {

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

        try {
            UpdateTask update = new UpdateTask(this);
            update.execute("/sample", LoganSquare.serialize(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFetchingRequestFinish(Integer result) {
        System.out.println(Integer.valueOf(result));
    }

}
