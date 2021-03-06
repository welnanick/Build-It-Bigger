package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.nickwelna.jokeviews.JokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {

    private static MyApi myApiService = null;
    private WeakReference<Context> contextRef;

    @Override
    protected String doInBackground(Context... params) {

        //simulates loading time for joke
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {

                        @Override
                        public void initialize(
                                AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {

                            abstractGoogleClientRequest.setDisableGZipContent(true);

                        }

                    });
            // end options for devappserver

            myApiService = builder.build();

        }

        contextRef = new WeakReference<>(params[0]);

        try {

            return myApiService.getJoke().execute().getData();

        }
        catch (IOException e) {

            return e.getMessage();

        }

    }

    @Override
    protected void onPostExecute(final String result) {

        final Context context = contextRef.get();

        ProgressBar progressBar = ((MainActivity) context).findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        MainActivityFragment fragment =
                (MainActivityFragment) ((MainActivity) context).getSupportFragmentManager()
                                                               .findFragmentById(R.id.fragment);

        if (fragment.interstitialAd != null) {

            fragment.interstitialAd.setAdListener(new AdListener() {

                @Override
                public void onAdClosed() {

                    Intent intent = new Intent(context, JokeActivity.class);
                    intent.putExtra(JokeActivity.JOKE_EXTRA, result);

                    context.startActivity(intent);

                }

            });

            if (fragment.interstitialAd.isLoaded()) {

                fragment.interstitialAd.show();


            }
        }
        else {

            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_EXTRA, result);

            context.startActivity(intent);

        }

    }

}
