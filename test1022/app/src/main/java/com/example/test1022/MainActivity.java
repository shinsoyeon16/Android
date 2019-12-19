package com.example.test1022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    Button btnNew, btnStyle;
    Button puzzleBtn[] = new Button[9];
    Integer puzzleIDs[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    int puzzleVal[]=new int[9];
    int i;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = (Chronometer) findViewById(R.id.chronometer1);
        btnNew = (Button)findViewById(R.id.btnNew);
        btnStyle = (Button)findViewById(R.id.btnStyle);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puzzleVal=new int[9];
                for(i=0;i<puzzleIDs.length;i++){
                    puzzleBtn[i] = (Button) findViewById(puzzleIDs[i]);
                }
                for(i = 0;i<9;i++) {
                    puzzleVal[i] = r.nextInt(9) + 1;
                    for (int j= 0; j < i; j++) {
                        if (puzzleVal[j] == puzzleVal[i]) {
                            i--;
                        }
                    }
                }
                for(i=0;i<puzzleIDs.length;i++){
                    puzzleBtn[i].setText(puzzleVal[i]);
                }
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();

            }
        });
    }
}
