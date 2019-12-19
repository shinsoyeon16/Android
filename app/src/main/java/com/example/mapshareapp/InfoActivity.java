package com.example.mapshareapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class InfoActivity extends Activity {
    Button btnRMenu, btnRreview;
    TextView tvRname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_info);
        btnRMenu = (Button) findViewById(R.id.btnRMenu);
        btnRreview = (Button) findViewById(R.id.btnRreview);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        LatLng latLng =  (LatLng) intent.getParcelableExtra("Position");

        btnRMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("State", "닫기버튼눌림");
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }

}
