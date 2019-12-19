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

public class JoinActivity extends Activity {
    Button btnJjoin;
    ImageButton btnJback;
    EditText etJid, etJpassword, etJcheck, etJname, etJphonenumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        btnJback = (ImageButton) findViewById(R.id.btnJback);
        btnJjoin = (Button) findViewById(R.id.btnJjoin);
        etJid =(EditText) findViewById(R.id.etJid);
        etJpassword =(EditText)findViewById(R.id.etJpassword);
        etJcheck =(EditText)findViewById(R.id.etJcheck);
        etJname =(EditText)findViewById(R.id.etJname);
        etJphonenumber =(EditText)findViewById(R.id.etJphonenumber);

        btnJjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etJid.getText().toString();
                String password = etJpassword.getText().toString();
                String check = etJcheck.getText().toString();
                String name = etJname.getText().toString();
                String phonenumber = etJphonenumber.getText().toString();
                if(id.isEmpty()|| password.isEmpty() || check.isEmpty() || phonenumber.isEmpty()) Toast.makeText(getApplicationContext(),"모든 항목을 입력해주십시오.", Toast.LENGTH_SHORT ).show();
                else if(!password.equals(check)) Toast.makeText(getApplicationContext(),"비밀번호를 잘못 입력하셨습니다.", Toast.LENGTH_SHORT ).show();
                else {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String[][] result =  new RequestDB().SendByHttp(id, password, name, phonenumber); // jsp페이지에 db요청해서 결과값 받아오기

                    if(result[0][0].equals("succed")){ // 가입 성공
                        Toast.makeText(getApplicationContext(), "["+id+"] 님 회원가입이 완료되었습니다.", Toast.LENGTH_SHORT ).show();
                        finish();
                    } else if(result[0][0].equals("failed")){ //가입 실패
                        Toast.makeText(getApplicationContext(), "이미 가입한 아이디입니다", Toast.LENGTH_LONG).show();
                        etJid.setText(""); etJpassword.setText(""); etJcheck.setText(""); etJname.setText("");  etJphonenumber.setText("");
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
