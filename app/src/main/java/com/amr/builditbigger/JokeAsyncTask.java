package com.amr.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.amr.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.amr.jokeactivity.JokeDisplayActivity;

import java.io.IOException;

/**
 * Created by amr on 10/28/17.
 */

public class JokeAsyncTask extends AsyncTask <Void, Void, String>{




        private static MyApi myApiService = null;
        private Context mContext;

        public JokeAsyncTask (Context context){
            mContext = context;
        }

        @Override
        protected String doInBackground(Void... params){
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)

                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });


                myApiService = builder.build();
            }

            try {
                return myApiService.sayHi().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Intent intent = new Intent(mContext, JokeDisplayActivity.class);

            intent.putExtra("joke", result);
            mContext.startActivity(intent);
        }
    }



