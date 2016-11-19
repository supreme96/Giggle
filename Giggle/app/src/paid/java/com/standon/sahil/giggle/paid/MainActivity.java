package com.standon.sahil.giggle.paid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.standon.sahil.giggle.JokeFetchTask;
import com.standon.sahil.giggle.JokeReceiver;
import com.standon.sahil.giggle.R;
import com.standon.sahil.giggle.jokeshowlib.JokeViewer;

public class MainActivity extends Activity implements JokeReceiver {

    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tellJoke(View view){
        JokeFetchTask task = new JokeFetchTask();
        task.execute(this);
    }

    @Override
    public void jokeFetched(String joke) {
        this.joke = joke;
        Intent intent = new Intent(this, JokeViewer.class);
        intent.putExtra("joke", this.joke);
        startActivity(intent);
    }
}
