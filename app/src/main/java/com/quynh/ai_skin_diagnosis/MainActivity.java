package com.quynh.ai_skin_diagnosis;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imgView;
    Button btnCamera;
    TextView txtResult;
    Bitmap bitmap;
    Classifier classifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = findViewById(R.id.imgView);
        btnCamera = findViewById(R.id.btnCamera);
        txtResult = findViewById(R.id.txtResult);

        // Khởi tạo classifier
        try {
            classifier = new Classifier(this);
        } catch (IOException e) {
            e.printStackTrace();
            txtResult.setText("Lỗi tải model!");
        }

        btnCamera.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 100);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(bitmap);
            predictImage(bitmap);
        }
    }

    private void predictImage(Bitmap bitmap) {
        if (classifier != null && bitmap != null) {
            txtResult.setText("Đang xử lý ảnh...");
            String result = classifier.predict(bitmap);
            txtResult.setText("Kết quả: " + result);
        } else {
            txtResult.setText("Chưa sẵn sàng!");
        }
    }
}