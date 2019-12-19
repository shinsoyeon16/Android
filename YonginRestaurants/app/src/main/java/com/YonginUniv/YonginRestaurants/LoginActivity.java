package com.YonginUniv.YonginRestaurants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class LoginActivity extends Activity {
    Button btnLlogin, btnLjoin;
    ImageButton btnLback;
    EditText etLid, etLpassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnLback = (ImageButton) findViewById(R.id.btnLback);
        btnLlogin = (Button) findViewById(R.id.btnLlogin);
        btnLjoin = (Button) findViewById(R.id.btnLjoin);
        etLid =(EditText) findViewById(R.id.etLid);
        etLpassword =(EditText)findViewById(R.id.etLpassword);

        btnLlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etLid.getText().toString().isEmpty()|| etLpassword.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),"모든 항목을 입력해주십시오.", Toast.LENGTH_SHORT ).show();
                else {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String[][] result =  new RequestDB().SendByHttp(etLid.getText().toString(), etLpassword.getText().toString()); // jsp페이지에 로그인 db요청해서 결과값 받아오기

                    if(result[0][0].equals("succed")){ // 로그인 성공
                        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id", etLid.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    } else if(result[0][0].equals("failed")){ //로그인 실패
                        Toast.makeText(getApplicationContext(), "정보를 정확하게 입력하세요.", Toast.LENGTH_LONG).show();
                        etLid.setText(""); etLpassword.setText("");
                    }
                }
            }
        });
        btnLjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
        btnLback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
