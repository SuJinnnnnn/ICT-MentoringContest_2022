package com.example.chart2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView tempText;
    TextView humidText;

    DatabaseReference cageStatus;
    DatabaseReference cageControl;

    private final String TAG = "MainActivityLog";

    int temp = 0;
    int humid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cageStatus = FirebaseDatabase.getInstance().getReference("cageStatus");
        cageControl = FirebaseDatabase.getInstance().getReference("cageControl");

        tempText = findViewById(R.id.temp);
        humidText = findViewById(R.id.humid);

        cageStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                temp = Integer.parseInt(dataSnapshot.child("temp").getValue().toString());
                humid = Integer.parseInt(dataSnapshot.child("humid").getValue().toString());
                tempText.setText("현재 온도 : "+temp+"도");
                humidText.setText("현재 온도 : "+humid+"%");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }

    public void coolerOn(View v) {
        cageControl = FirebaseDatabase.getInstance().getReference("cageControl");
        cageControl.child("cooler").setValue(true);
        Toast.makeText(getApplicationContext(),"냉방기를 켭니다!", Toast.LENGTH_LONG).show();
    }

    public void coolerOff(View v){
        cageControl = FirebaseDatabase.getInstance().getReference("cageControl");
        cageControl.child("cooler").setValue(false);
        Toast.makeText(getApplicationContext(),"냉방기를 끕니다!", Toast.LENGTH_LONG).show();
    }

    public void heaterOn(View v) {
        cageControl = FirebaseDatabase.getInstance().getReference("cageControl");
        cageControl.child("heater").setValue(true);
        Toast.makeText(getApplicationContext(),"난방을 켭니다!", Toast.LENGTH_LONG).show();
    }

    public void heaterOff(View v){
        cageControl = FirebaseDatabase.getInstance().getReference("cageControl");
        cageControl.child("heater").setValue(false);
        Toast.makeText(getApplicationContext(),"난방을 끕니다!", Toast.LENGTH_LONG).show();
    }
}
