package com.standon.sahil.giggle;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.standon.sahil.giggle.backend.myApi.MyApi;
import com.standon.sahil.giggle.jokeshowlib.JokeViewer;

import java.io.IOException;

/**
 * Created by sahil on 25/8/16.
 */

public class JokeFetchTask extends AsyncTask<JokeReceiver, Void, String> {


    private JokeReceiver reciever;
    private static MyApi myApiService = null;
    Context context;

    @Override
    protected String doInBackground(JokeReceiver... receivers) {

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.1.6:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        reciever = receivers[0];
        try {
            return myApiService.giveJoke().execute().getData();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Log.v("sahil joke: ", result);
        reciever.jokeFetched(result);
        /*Intent intent = new Intent(context, JokeViewer.class);
        intent.putExtra("joke", result);
        context.startActivity(intent);*/
    }

}

interface JokeReceiver{
    public void jokeFetched(String joke);
}