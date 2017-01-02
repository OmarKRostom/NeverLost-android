package com.borio;

import android.os.AsyncTask;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestTask extends AsyncTask<String, Void, Borio> {

    private static String TAG = RequestTask.class.getSimpleName();
    public AsyncRequestResponse delegate = null;

    private OkHttpClient client = new OkHttpClient();
    private String urlString;

//    public HandleRequestTask() {
//        super();
//    }

    public RequestTask(AsyncRequestResponse delegate) {
        super();
        this.delegate = delegate;
    }

    @Override
    protected Borio doInBackground(String... params) {
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
            return LoganSquare.parse(jsonResult, Borio.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Borio result) {
        if (delegate != null) {
            delegate.onFetchingRequestFinish(result);
        }
    }

    public interface AsyncRequestResponse {
        void onFetchingRequestFinish(Borio result);
    }

}
