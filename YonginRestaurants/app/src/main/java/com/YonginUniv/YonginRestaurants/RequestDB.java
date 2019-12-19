package com.YonginUniv.YonginRestaurants;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import static java.lang.System.exit;

public class RequestDB {
    public String[][] SendByHttp() { //  식당 정보 요청시 실행되는 메소드
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String URL = "http://192.168.0.2:8080/YonginRestaurantsServer/client/restaurantsListRequest.jsp";
            HttpPost post = new HttpPost(URL);
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return jsonParserList(result);
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return new String[][]{};
        }
    }
    public String[][] SendByHttp(String id) { //  회원 정보 요청시 실행되는 메소드
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String URL = "http://192.168.0.2:8080/YonginRestaurantsServer/client/mypageRequest.jsp?id="+id;
            HttpPost post = new HttpPost(URL);
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return jsonParserList(result);
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return new String[][]{};
        }
    }
    public String[][] SendByHttp(String id, String password) { // login 요청시 실행되는 메소드
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String URL = "http://192.168.0.2:8080/YonginRestaurantsServer/client/loginRequest.jsp";
            HttpPost post = new HttpPost(URL + "?id=" + id+"&pw="+password);
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return jsonParserList(result);
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return new String[][]{};
        }
    }
    public String[][] SendByHttp(String id, String password, String name, String phonenumber) { // join 요청시 실행되는 메소드
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String URL = "http://192.168.0.2:8080/YonginRestaurantsServer/client/joinRequest.jsp";
            HttpPost post = new HttpPost(URL + "?id=" + id+"&pw="+password+ "&name=" + name+ "&phonenumber=" + phonenumber);
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return jsonParserList(result);
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return new String[][]{};
        }
    }
    public String[][] SendByHttp(String id, String password, String name, String phonenumber, int i) { // update 요청시 실행되는 메소드
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String URL = "http://192.168.0.2:8080/YonginRestaurantsServer/client/updateRequest.jsp";
            HttpPost post = new HttpPost(URL + "?id=" + id+"&pw="+password+ "&name=" + name+ "&phonenumber=" + phonenumber);
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return jsonParserList(result);
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return new String[][]{};
        }
    }
    public String[][] SendByHttp(String id, int i) { // logout 요청시 실행되는 메소드
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String URL = "http://192.168.0.2:8080/YonginRestaurantsServer/client/logoutRequest.jsp";
            HttpPost post = new HttpPost(URL + "?id=" + id);
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return jsonParserList(result);
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return new String[][]{};
        }
    }
    public String[][] SendByHttp(String id, String code, int i) { //  즐겨찾기 요청시 실행되는 메소드
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String URL = "http://192.168.0.2:8080/YonginRestaurantsServer/client/favoriteRequest.jsp";
            HttpPost post = new HttpPost(URL + "?id=" + id+"&code="+code+"&data="+i);
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return jsonParserList(result);
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return new String[][]{};
        }
    }
    public String[][] jsonParserList(String pRecvServerPage){
        Log.i("서버에서 받은 전체 내용", pRecvServerPage);

        try{
            JSONObject json = new JSONObject(pRecvServerPage);
            JSONArray jArr = json.getJSONArray("List");

            String[] jsonName = {"result1","result2","result3"};
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
