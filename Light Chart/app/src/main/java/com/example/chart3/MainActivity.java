package com.example.chart3;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView lightText;

    DatabaseReference cageStatus;
    DatabaseReference cageControl;

    private final String TAG = "MainActivityLog";

    int light = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cageStatus = FirebaseDatabase.getInstance().getReference("cageStatus");
        cageControl = FirebaseDatabase.getInstance().getReference("cageControl");

        progressBar = findViewById(R.id.progress2);
        lightText = findViewById(R.id.light);

        cageStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                light = Integer.parseInt(dataSnapshot.child("lux").getValue().toString());
                light = light / 10;
                progressBar.setProgress(light);
                lightText.setText(light+"%");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }

    public void open(View v) {
        cageControl = FirebaseDatabase.getInstance().getReference("cageControl");
        cageControl.child("curtain").setValue(1);
        Toast.makeText(getApplicationContext(),"커튼이 열립니다!", Toast.LENGTH_LONG).show();
    }

    public void close(View v){
        cageControl = FirebaseDatabase.getInstance().getReference("cageControl");
        cageControl.child("curtain").setValue(0);
        Toast.makeText(getApplicationContext(),"커튼이 닫힙니다!", Toast.LENGTH_LONG).show();
    }
}
