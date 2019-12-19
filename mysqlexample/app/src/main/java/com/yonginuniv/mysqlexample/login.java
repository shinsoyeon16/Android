package com.yonginuniv.mysqlexample;

import android.app.Activity;
import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class login extends Activity {

  EditText edit_id, edit_pw;
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
                edit_id = (EditText) findViewById(R.id.login_id);
                edit_pw = (EditText) findViewById(R.id.login_pw);
    }

    public void login_btn(View view) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String sMessage = edit_id.getText().toString();
        String result = SendByHttp(sMessage);

        String[][] parsedData = jsonParserList(result);

        if(parsedData[0][0].equals("succed"))
        {
            Toast.makeText(login.this, "로그인 성공", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if(parsedData[0][0].equals("failed")){
            Toast.makeText(login.this, "로그인 실패. 다시입력하세요.", Toast.LENGTH_LONG).show();
        }
    }
    private String SendByHttp(String msg) {
        if (msg == null) {
            msg = "";
        }
        String URL = "http://192.168.0.2:8080/YonginRestaurantsServer/User.jsp"; //자신의 웹서버 주소를 저장합니다.
        DefaultHttpClient client = new DefaultHttpClient();//HttpClient 통신을 합니다.
        try {
            HttpPost post = new HttpPost(URL + "?id=" + edit_id.getText().toString()+"&pw="+edit_pw.getText().toString());
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return "";
        }
    }

    //받아온 데이터를 파싱하는 부분입니다.
    public String[][] jsonParserList(String pRecvServerPage){
        Log.i("서버에서 받은 전체 내용", pRecvServerPage);

        try{ //아까말한 {"List":[{"data1":"sfasf".""data2:"sdfsdf"}]} 이형태를 분해하는 과정입니다.
            JSONObject json = new JSONObject(pRecvServerPage);
            JSONArray jArr = json.getJSONArray("List");

            String[] jsonName = {"msg1","msg2","msg3"};
            String[][] parseredData = new String[jArr.length()][jsonName.length];
            for(int i = 0; i<jArr.length();i++){
                json = jArr.getJSONObject(i);
                for (int j=0;j<jsonName.length; j++){
                    parseredData[i][j] = json.getString(jsonName[j]);
                }

            }
            for(int i=0;i<parseredData.length;i++)
            {
                Log.i("JSON을 분석한 데이터"+i+":",parseredData[i][0]);
                Log.i("JSON을 분석한 데이터"+i+":",parseredData[i][1]);
                Log.i("JSON을 분석한 데이터"+i+":",parseredData[i][2]);
            }
            return parseredData;

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}