package com.example.mapshareapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    // 사용할 변수 선언
    TextView tvLocation;
    ImageButton btnGps, btnSearch, btnMenu;
    GoogleMap map;
    HashMap<String, LatLng> restaurants = new HashMap<String, LatLng>(); // 음식점 코드와 좌표를 저장할 변수
    String session = new String();
    private static final int LOGIN=1, RESTAURANT_INFO=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 사용할 위젯 변수 매칭
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        btnGps = (ImageButton) findViewById(R.id.btnGps);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); // onMapReady 함수 호출로 지도초기값 설정하기.

        //  gps 버튼 클릭시 현재위치로 지도 설정하는 리스너
        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
                if (Build.VERSION.SDK_INT >= 23 && permission != PackageManager.PERMISSION_GRANTED) { // 위치 정보 미사용인 경우
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0); //위치 권한 동의 띄우기
                } else { // 위치 정보 사용인 경우
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); //현재위치 가져오기
                    map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));  // 현재위치로 지도 설정
                    tvLocation.setText("위도 : " + location.getLatitude() + "  경도: " + location.getLongitude());
                }
            }
        });

        // 메뉴 버튼 클릭시 팝업메뉴 노출시키는 리스너
        btnMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                MenuInflater mInflater = popupMenu.getMenuInflater();
                Menu menu = popupMenu.getMenu();
                // 세션 유무에 따라 메뉴를 다르게 설정하기
                if(session.isEmpty()) mInflater.inflate(R.menu.menu_logout, menu);
                else mInflater.inflate(R.menu.menu_login, menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mLogin: {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivityForResult(intent, LOGIN);
                                break;
                            }
                            case R.id.mLogout: {
                                Toast.makeText(getApplicationContext(), "로그아웃", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "["+session+"] 님 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                                session=null;
                                break;
                            }
                            case R.id.mMypage: {
                                Toast.makeText(getApplicationContext(), "내정보", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            case R.id.mFavorite: {
                                Toast.makeText(getApplicationContext(), "즐겨찾기", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            case R.id.mSuggest: {
                                Toast.makeText(getApplicationContext(), "맛집신청", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }  // onCreat 끝

    // 지도를 초기값으로 설정하는 함수
    public void onMapReady(GoogleMap googleMap){
        map = googleMap;
        LatLng yonginUniv = new LatLng (37.22671985702962,127.16784838258968);
        restaurants.put("용인", yonginUniv);//나중에 지우세요~~~~
        restaurants.put("환과대", new LatLng(37.227634, 127.165914));//나중에 지우세요~~~~
        restaurants.put("경행대", new LatLng(37.225289, 127.167443));//나중에 지우세요~~~~
        restaurants.put("문예대", new LatLng(37.226763, 127.169685));//나중에 지우세요~~~~
        restaurants.put("인성관", new LatLng(37.228001, 127.169626));//나중에 지우세요~~~~
        map.setMinZoomPreference(15);
        map.moveCamera(CameraUpdateFactory.newLatLng(yonginUniv));
        map.setOnMarkerClickListener(this); // 클릭리스너 지정
        readRestaurants();
        setMarkers(); // 마커들을 추가하는 함수
    }

    // 식당 정보 불러오는 함수
    public void readRestaurants() {

    }

    // 식당 마커들을 지도에 추가하는 함수
    public void setMarkers(){
        for(String key : restaurants.keySet()){
            map.addMarker(new MarkerOptions().position(restaurants.get(key)).title(key).icon(BitmapDescriptorFactory.fromResource(R.drawable.location)));
        }
    }

    // 마커 클릭시 식당정보 띄우는 메소드 (인텐트)
    public boolean onMarkerClick(Marker marker) {
        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        intent.putExtra("Lat", marker.getPosition().latitude);
        intent.putExtra("Long", marker.getPosition().longitude);
        intent.putExtra("Position", marker.getPosition());
        intent.putExtra("Title", marker.getTitle());
        startActivityForResult(intent, RESTAURANT_INFO);
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESTAURANT_INFO && resultCode==RESULT_OK){
            String msg = data.getStringExtra("State");
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
        if(requestCode==LOGIN && resultCode==RESULT_OK){
            session=data.getStringExtra("id");
            Toast.makeText(getApplicationContext(), "["+session+"] 님 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }
} // MainActivity 끝

