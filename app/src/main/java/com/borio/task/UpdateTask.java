package com.borio.task;

import android.os.AsyncTask;

import com.borio.Utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateTask extends AsyncTask<String, Void, Integer> {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static String TAG = UpdateTask.class.getSimpleName();

    public UpdateResponse delegate = null;

    private OkHttpClient client = new OkHttpClient();
    private String urlString;
    private String jsonString;

//    public UpdateTask() {
//        super();
//    }

    public UpdateTask(UpdateResponse delegate) {
        super();
        this.delegate = delegate;
    }

    @Override
    protected Integer doInBackground(String... params) {
        if (params.length != 2) {
            return null;
        } else {
            this.urlString = Utils.serverURL + params[0];
            this.jsonString = params[1];
        }

        RequestBody body = RequestBody.create(JSON, jsonString);
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.code();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (delegate != null) {
            delegate.onFetchingUpdateFinish(result);
        }
    }

    public interface UpdateResponse {
        void onFetchingUpdateFinish(Integer result);
    }

}
