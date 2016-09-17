package com.standon.sahil.giggle.jokeshowlib;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeViewer extends Activity {

    TextView jokeViewTextView;
    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_viewer);
        jokeViewTextView = (TextView) findViewById(R.id.joke_view_tv);
        joke = getIntent().getStringExtra("joke");
        if (joke == null){
            jokeViewTextView.setText("No joke retrieved :( \n Local Server Offline");
        }else{
            jokeViewTextView.setText(joke);
        }
    }
}
