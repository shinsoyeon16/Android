package com.example.lesson0917;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText e1, e2;
    Button b1, b2, b3, b4, b5;
    TextView tr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.BtnAdd);
        b2 = (Button) findViewById(R.id.BtnMinus);
        b3 = (Button) findViewById(R.id.BtnMultifle);
        b4 = (Button) findViewById(R.id.BtnDivide);
        b5 = (Button) findViewById(R.id.BtnNamu);
        e1 = (EditText) findViewById(R.id.Edit1);
        e2 = (EditText) findViewById(R.id.Edit2);
        tr = (TextView) findViewById(R.id.TextResult);
        b1.setOnClickListener(new View.OnClickListener() { // 더하기 버튼 클릭 이벤트
            @Override
            public void onClick(View v) {
                if (e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    double num1 = Integer.parseInt(e1.getText().toString());
                    double num2 = Integer.parseInt(e2.getText().toString());
                    double result = num1 + num2;
                    tr.setText("Result : " + result);

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() { // 빼기 버튼 클릭 이벤트
            @Override
            public void onClick(View v) {
                if (e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    double num1 = Integer.parseInt(e1.getText().toString());
                    double num2 = Integer.parseInt(e2.getText().toString());
                    double result = num1 - num2;
                    tr.setText("Result : " + result);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() { // 곱하기 버튼 클릭 이벤트
            @Override
            public void onClick(View v) {
                if (e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    double num1 = Integer.parseInt(e1.getText().toString());
                    double num2 = Integer.parseInt(e2.getText().toString());
                    double result = num1 * num2;
                    tr.setText("Result : " + result);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() { // 나누기 버튼 클릭 이벤트
            @Override
            public void onClick(View v) {
                if (e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(e2.getText().toString()) ==0.0){
                    Toast.makeText(getApplicationContext(), "0으로 나눌수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    double num1 = Integer.parseInt(e1.getText().toString());
                    double num2 = Integer.parseInt(e2.getText().toString());
                    double result = num1 / num2;
                    tr.setText("Result : " + result);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() { // 나머지 버튼 클릭 이벤트
            @Override
            public void onClick(View v) {
                if (e1.getText().toString() == "" || e2.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    double num1 = Integer.parseInt(e1.getText().toString());
                    double num2 = Integer.parseInt(e2.getText().toString());
                    double result = num1 % num2;
                    tr.setText("Result : " + result);
                }
            }
        });
    }
}
