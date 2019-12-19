package com.example.lesson1001;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnOpen);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb1 = (RadioButton) findViewById(R.id.rbsecond);
                RadioButton rb2 = (RadioButton) findViewById(R.id.rbthird);
                if( rb1.isChecked()){
                   Intent intent = new Intent(getApplicationContext(),
                            SecondActivity.class);
                    startActivity(intent);
               } else if(rb2.isChecked()){
                    Intent intent = new Intent(getApplicationContext(),
                            ThirdActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
