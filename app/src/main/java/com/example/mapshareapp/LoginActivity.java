package com.example.mapshareapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
                String id = etLid.getText().toString();
                String password = etLpassword.getText().toString();
                if(id.isEmpty()|| password.isEmpty()) Toast.makeText(getApplicationContext(),"모든 항목을 입력해주십시오.", Toast.LENGTH_SHORT ).show();
                else {
                    boolean loginable=true;
                    if(loginable){ // 로그인 성공시 액션
                        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id", etLid.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
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


