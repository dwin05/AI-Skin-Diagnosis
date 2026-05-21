package com.quynh.ai_skin_diagnosis;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TipsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_detail);

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