package com.standon.sahil.giggle.free;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.standon.sahil.giggle.JokeFetchTask;
import com.standon.sahil.giggle.JokeReceiver;
import com.standon.sahil.giggle.R;
import com.standon.sahil.giggle.jokeshowlib.JokeViewer;

public class MainActivity extends Activity implements JokeReceiver{

    private String joke;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
    }

    public void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);
    }

    public void tellJoke(View view){
        requestNewInterstitial();
        JokeFetchTask task = new JokeFetchTask();
        task.execute(this);
    }

    @Override
    public void jokeFetched(String joke) {
        this.joke = joke;
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    requestNewInterstitial();
                    Intent intent = new Intent(MainActivity.this, JokeViewer.class);
                    intent.putExtra("joke", MainActivity.this.joke);
                    startActivity(intent);
                }
            });
        }
        else{
            Intent intent = new Intent(this, JokeViewer.class);
            intent.putExtra("joke", joke);
            startActivity(intent);
        }

    }
}
