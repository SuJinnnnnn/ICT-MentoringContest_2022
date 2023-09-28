package com.example.camerabutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onButton1Cliked(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://192.168.128.168:81/stream"));
                startActivity(i);
    }
}