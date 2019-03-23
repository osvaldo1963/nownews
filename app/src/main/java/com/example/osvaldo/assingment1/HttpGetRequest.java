package com.example.osvaldo.assingment1;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpGetRequest extends AsyncTask<String, Void, String> {

    List<JSONObject> jsonArticles;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.jsonArticles = new ArrayList<>();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String result = run(strings[0]);
            JSONObject json = new JSONObject(result);
            JSONArray articles = json.getJSONArray("articles");

            for(int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.getJSONObject(i);
                Log.e("articles: ", String.valueOf(article));
                this.jsonArticles.add(article);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);



    }

}
