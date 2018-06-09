package com.nickwelna.jokeviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeTextView = findViewById(R.id.joke_text_view);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String joke = null;
        if (extras != null) {

            joke = extras.getString(JOKE_EXTRA);

        }

        jokeTextView.setText(joke);

    }

}
