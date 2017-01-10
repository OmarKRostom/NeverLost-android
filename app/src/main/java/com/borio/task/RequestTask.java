package com.borio.task;

import android.os.AsyncTask;

import com.bluelinelabs.logansquare.LoganSquare;
import com.borio.Utils;
import com.borio.data.Data;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestTask extends AsyncTask<String, Void, Data> {

    private static String TAG = RequestTask.class.getSimpleName();
    public RequestResponse delegate = null;

    private OkHttpClient client = new OkHttpClient();
    private String urlString;

//    public RequestTask() {
//        super();
//    }

    public RequestTask(RequestResponse delegate) {
        super();
        this.delegate = delegate;
    }

    @Override
    protected Data doInBackground(String... params) {
        if (params.length != 1) {
            return null;
        } else {
            this.urlString = Utils.serverURL + params[0];
        }

        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String jsonResult = response.body().string();
            return LoganSquare.parse(jsonResult, Data.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Data result) {
        if (delegate != null) {
            delegate.onFetchingRequestFinish(result);
        }
    }

    public interface RequestResponse {
        void onFetchingRequestFinish(Data result);
    }

}
