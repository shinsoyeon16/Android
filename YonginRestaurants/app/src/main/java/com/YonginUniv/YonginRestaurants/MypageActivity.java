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

public class MypageActivity extends Activity {
    Button btnMUpdate;
    ImageButton btnMback;
    EditText etMid, etMpassword, etMcheck, etMname, etMphonenumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        btnMback = (ImageButton) findViewById(R.id.btnMback);
        btnMUpdate = (Button) findViewById(R.id.btnMUpdate);
        etMid =(EditText) findViewById(R.id.etMid);
        etMpassword =(EditText)findViewById(R.id.etMpassword);
        etMcheck =(EditText)findViewById(R.id.etMcheck);
        etMname =(EditText)findViewById(R.id.etMname);
        etMphonenumber =(EditText)findViewById(R.id.etMphonenumber);
        Intent intent = getIntent();
        etMid.setText(intent.getStringExtra("id"));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String[][] result = new RequestDB().SendByHttp(etMid.getText().toString());
        etMname.setText(result[0][0]);
        etMphonenumber.setText(result[0][1]);

        btnMUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etMid.getText().toString();
                String password = etMpassword.getText().toString();
                String check = etMcheck.getText().toString();
                String name = etMname.getText().toString();
                String phonenumber = etMphonenumber.getText().toString();

                if(password.isEmpty() || check.isEmpty() || phonenumber.isEmpty()) Toast.makeText(getApplicationContext(),"모든 항목을 입력해주십시오.", Toast.LENGTH_SHORT ).show();
                else if(!password.equals(check)) {Toast.makeText(getApplicationContext(),"비밀번호를 잘못 입력하셨습니다.", Toast.LENGTH_SHORT ).show(); etMpassword.setText(""); etMcheck.setText("");}
                else {
                    String[][] result2 =  new RequestDB().SendByHttp(id, password, name, phonenumber, 0); // jsp페이지에 db요청해서 정보 수정하기

                    if(result2[0][0].equals("succed")){ //  수정 성공
                        Toast.makeText(getApplicationContext(), "["+id+"] 님 회원 정보 수정이 완료되었습니다.", Toast.LENGTH_SHORT ).show();
                        finish();
                    } else { // 수정 실패
                        Toast.makeText(getApplicationContext(), "수정 실패. 다시 시도해주십시오.", Toast.LENGTH_LONG).show();
                        etMpassword.setText(""); etMcheck.setText("");
                    }
                }
            }
        });
        btnMback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
