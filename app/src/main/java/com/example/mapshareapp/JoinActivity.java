package com.example.mapshareapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class JoinActivity extends Activity {
    Button btnJjoin;
    ImageButton btnJback;
    EditText etJid, etJpassword, etJcheck, etJphonenumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        btnJback = (ImageButton) findViewById(R.id.btnJback);
        btnJjoin = (Button) findViewById(R.id.btnJjoin);
        etJid =(EditText) findViewById(R.id.etJid);
        etJpassword =(EditText)findViewById(R.id.etJpassword);
        etJcheck =(EditText)findViewById(R.id.etJcheck);
        etJphonenumber =(EditText)findViewById(R.id.etJphonenumber);

        btnJjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etJid.getText().toString();
                String password = etJpassword.getText().toString();
                String check = etJcheck.getText().toString();
                String phonenumber = etJphonenumber.getText().toString();
                if(id.isEmpty()|| password.isEmpty() || check.isEmpty() || phonenumber.isEmpty()) Toast.makeText(getApplicationContext(),"모든 항목을 입력해주십시오.", Toast.LENGTH_SHORT ).show();
                else if(!password.equals(check)) Toast.makeText(getApplicationContext(),"비밀번호를 잘못 입력하셨습니다.", Toast.LENGTH_SHORT ).show();
                else {
                    boolean joinableId=true;
                    if(joinableId){ // 가입 성공시 액션
                        Toast.makeText(getApplicationContext(), "["+id+"] 님 회원가입이 완료되었습니다.", Toast.LENGTH_SHORT ).show();
                        finish();
                    }
                }
            }
        });
        btnJback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
