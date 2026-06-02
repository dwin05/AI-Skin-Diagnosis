package com.quynh.ai_skin_diagnosis;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Window window = getWindow();
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController
                (window, window.getDecorView());
        controller.setAppearanceLightStatusBars(true);
        //true để ép chữ và icon hệ thống chuyển sang màu ĐEN
        //thanh trạng thái có màu trắng hoàn toàn tiệp với nền web:
        window.setStatusBarColor(Color.WHITE);

        webView = findViewById(R.id.webView);
        String url = getIntent().getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}