package com.YonginUniv.YonginRestaurants;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RestaurantInfoActivity extends Activity {
    Button btnRMenu, btnRreview;
    TextView tvRname, tvRaddress, tvRnumber, tvRhomepage;
    ImageButton imgRfavorite;
    ImageView imageR;
    String name;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_info);
        btnRMenu = (Button) findViewById(R.id.btnRMenu);
        btnRreview = (Button) findViewById(R.id.btnRreview);
        tvRname = (TextView) findViewById(R.id.tvRname);
        tvRaddress = (TextView) findViewById(R.id.tvRaddress);
        tvRnumber = (TextView) findViewById(R.id.tvRnumber);
        tvRhomepage = (TextView) findViewById(R.id.tvRhomepage);
        imgRfavorite = (ImageButton) findViewById(R.id.imgRfavorite);

        intent = getIntent();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String[][] result =  new RequestDB().SendByHttp(intent.getStringExtra("id"), intent.getStringExtra("code"), 0);
        if(result[0][0].equals("true")) imgRfavorite.setImageResource(R.drawable.favorite_on);
        else if(result[0][0].equals("false")) imgRfavorite.setImageResource(R.drawable.favorite_off);

        name = intent.getStringExtra("name");
        tvRname.setText(name);
        tvRaddress.setText(intent.getStringExtra("address"));
        tvRnumber.setText(intent.getStringExtra("number"));
        tvRhomepage.setText(intent.getStringExtra("homepage"));
        btnRMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://search.naver.com/search.naver?query="+name);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btnRreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://search.naver.com/search.naver?query="+name+" 리뷰");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        imgRfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.getStringExtra("id").isEmpty()){
                    Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show(); return;
                }
                String[][] result =  new RequestDB().SendByHttp(intent.getStringExtra("id"), intent.getStringExtra("code"), 1);
                if(result[0][0].equals("true")) imgRfavorite.setImageResource(R.drawable.favorite_on);
                else if(result[0][0].equals("false")) imgRfavorite.setImageResource(R.drawable.favorite_off);
            }
        });
    }

}
