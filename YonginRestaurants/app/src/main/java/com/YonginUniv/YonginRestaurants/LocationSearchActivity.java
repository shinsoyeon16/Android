package com.YonginUniv.YonginRestaurants;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;


public class LocationSearchActivity extends AppCompatActivity {
    private WebView webview;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_search);
        init();
        handler = new Handler();
    }

    public void init() {
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.addJavascriptInterface(new AndroidBridge(), "TestApp");
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl("http://192.168.0.2:8080/YonginRestaurantsServer/client/locationsearch.jsp");
    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("data", String.format("(%s) %s %s", arg1, arg2, arg3));
                    setResult(RESULT_OK, intent);
                    finish();
                    init();
                }
            });
        }
    }
}


