package com.standon.sahil.giggle.free;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.standon.sahil.giggle.JokeFetchTask;
import com.standon.sahil.giggle.JokeReceiver;
import com.standon.sahil.giggle.R;
import com.standon.sahil.giggle.jokeshowlib.JokeViewer;

public class MainActivity extends Activity implements JokeReceiver{
    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "THIS IS THE FREE", Toast.LENGTH_SHORT).show();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    public void tellJoke(View view){
        //Toast.makeText(this, new JavaSrc().retrieveJoke(), Toast.LENGTH_SHORT).show();
        JokeFetchTask task = new JokeFetchTask();
        task.execute(this);
        Intent intent = new Intent(this, JokeViewer.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
    }

    @Override
    public void jokeFetched(String joke) {
        this.joke = joke;
    }
}
