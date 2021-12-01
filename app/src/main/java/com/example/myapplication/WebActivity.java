package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        Intent intent = getIntent();
        String number=intent.getStringExtra("number");
        String stuname=intent.getStringExtra("stuname");
        String distress=intent.getStringExtra("distress");
        //获得控件
        WebView webView = findViewById(R.id.webView);
        //访问网页
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/cewen.html?"+number+"="+stuname+"="+distress);
        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                //返回true
                return true;
            }
        });
    }
}