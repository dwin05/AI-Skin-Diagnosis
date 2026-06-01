package com.quynh.ai_skin_diagnosis;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class TipsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_detail);

        Window window = getWindow();
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());
        // Đặt thành true để ép chữ và icon hệ thống chuyển sang màu ĐEN
        controller.setAppearanceLightStatusBars(true);

        //thanh trạng thái có màu trắng hoàn toàn tiệp với nền web:
        window.setStatusBarColor(Color.WHITE);

        ImageView imgDetail = findViewById(R.id.imgDetail);
        TextView txtTitle = findViewById(R.id.txtDetailTitle);
        TextView txtContent = findViewById(R.id.txtDetailContent);

        imgDetail.setImageResource(
                getIntent().getIntExtra("image", 0)
        );

        txtTitle.setText(
                getIntent().getStringExtra("title")
        );

        txtContent.setText(
                getIntent().getStringExtra("content")
        );
    }
}