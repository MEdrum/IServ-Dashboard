package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        ConnectivityManager connman = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connman.getActiveNetworkInfo();
        Boolean conn = networkInfo != null && networkInfo.isConnectedOrConnecting();
        Toast.makeText(MainActivity.this, conn.toString(), Toast.LENGTH_SHORT).show();
        try {
            URL url = new URL("http://google.com");
            URLConnection urlConn = url.openConnection();
            if(urlConn instanceof HttpURLConnection)
            {
                HttpURLConnection httpConn = (HttpURLConnection) urlConn;
                httpConn.setRequestMethod("GET");
                httpConn.connect();
                int returncode = httpConn.getResponseCode();
                if(returncode == HttpURLConnection.HTTP_OK)
                {
                    InputStream in = httpConn.getInputStream();
                }
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}