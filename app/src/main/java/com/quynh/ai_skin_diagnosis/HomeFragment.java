package com.quynh.ai_skin_diagnosis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ImageView imgView, imgCameraIcon;
    Button btnCamera, btnGallery, btnAnalyze;
    TextView txtResult, txtBenign, txtMalignant, tvAnh;
    Bitmap bitmap;
    Classifier classifier;

    public HomeFragment() {}
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Ánh xạ các View từ giao diện XML
        imgView = view.findViewById(R.id.imgView);
        imgCameraIcon = view.findViewById(R.id.imgCameraIcon);
        btnCamera = view.findViewById(R.id.btnCamera);
        btnGallery = view.findViewById(R.id.btnGallery);
        btnAnalyze = view.findViewById(R.id.btnAnalyze);
        txtResult = view.findViewById(R.id.txtResult);
        txtBenign = view.findViewById(R.id.txtBenign);
        txtMalignant = view.findViewById(R.id.txtMalignant);
        tvAnh = view.findViewById(R.id.tvAnh);
        // Tải model AI lên
        try {
            classifier = new Classifier(requireContext());
        } catch (IOException e) {
            e.printStackTrace();
            txtResult.setText("Lỗi tải AI model!");
        }
        // kt quyền
        btnCamera.setOnClickListener(v -> {
            if (androidx.core.content.ContextCompat.checkSelfPermission(
                    requireContext(), android.Manifest.permission.CAMERA) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                mobaCamera();
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.CAMERA);
            }
        });

        // GALLERY
        btnGallery.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(intent);
        });

        // ANALYZE
        btnAnalyze.setOnClickListener(v -> {
            if (bitmap != null) {
                predictImage(bitmap);
            } else {
                txtResult.setText("Vui lòng chọn ảnh!");
            }
        });
    }
    // Bộ đăng ký xin quyền truy cập Camera từ phía người dùng
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Người dùng bấm "Cho phép" -> Gọi hàm mở camera
                    mobaCamera();
                } else {
                    // Người dùng bấm "Từ chối"
                    txtResult.setText("Bạn cần cấp quyền Camera để chụp ảnh!");
                }
            });

    // Hàm gọi Intent mở ứng dụng Camera an toàn
    private void mobaCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            cameraLauncher.launch(intent);
        } else {
            try {
                cameraLauncher.launch(intent);
            } catch (Exception e) {
                txtResult.setText("Không tìm thấy ứng dụng Camera trên hệ thống!");
            }
        }
    }

    // XỬ LÝ KẾT QUẢ KHI CHỤP ẢNH TỪ CAMERA XONG
    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == android.app.Activity.RESULT_OK && result.getData() != null) {
                            Bundle extras = result.getData().getExtras();
                            if (extras != null) {
                                bitmap = (Bitmap) extras.get("data");
                                //bitmap = cropToSquare(rawBitmap);
                                imgView.setImageBitmap(bitmap);
                                tvAnh.setVisibility(View.GONE);
                                imgCameraIcon.setVisibility(View.GONE);
                            }
                        }
                    });

    // XỬ LÝ KẾT QUẢ KHI CHỌN ẢNH TỪ THƯ VIỆN (GALLERY) XONG
    private final ActivityResultLauncher<Intent> galleryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == android.app.Activity.RESULT_OK && result.getData() != null) {
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(
                                        requireActivity().getContentResolver(),
                                        result.getData().getData()
                                );
                                imgView.setImageBitmap(bitmap);
                                tvAnh.setVisibility(View.GONE);
                                imgCameraIcon.setVisibility(View.GONE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
    // cắt ảnh vuông
    private Bitmap cropToSquare(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // Tìm kích thước của cạnh ngắn hơn
        int newWidth = Math.min(width, height);
        int newHeight = newWidth;
        // Tính toán tọa độ điểm bắt đầu để cắt từ chính giữa bức ảnh
        int cropX = (width - newWidth) / 2;
        int cropY = (height - newHeight) / 2;
        // Cắt ảnh theo tỉ lệ vuông 1:1 chuẩn
        Bitmap squareBitmap = Bitmap.createBitmap(bitmap, cropX, cropY, newWidth, newHeight);
        return squareBitmap;
    }

    // AI PREDICT
    private void predictImage(Bitmap bitmap) {
        PredictionResult result = classifier.predict(bitmap);
        txtResult.setText("Kết quả: " + result.getLabel());
        txtBenign.setText(String.format("Lành tính: %.1f%%", result.getBenignPercent()));
        txtMalignant.setText(String.format("Ác tính: %.1f%%", result.getMalignantPercent()));
        // Tải lịch sử cũ
        ArrayList<HistoryItem> historyList = HistoryManager.loadHistory(requireContext());
        // Lưu ảnh tạm thời
        String imagePath = saveImage(bitmap);
        // Lấy thời gian hiện tại
        String currentDate = java.text.DateFormat.getDateTimeInstance().format(new java.util.Date());
        // Tính toán phần trăm lưu theo nhãn phù hợp
        float percent;
        if (result.getLabel().equals("Lành tính")) {
            percent = result.getBenignPercent();
        } else {
            percent = result.getMalignantPercent();
        }

        // Đẩy bản ghi mới lên đầu danh sách và lưu lại
        historyList.add(0, new HistoryItem(imagePath, result.getLabel(),
                percent, currentDate));
        HistoryManager.saveHistory(requireContext(), historyList);
    }

    // Hàm mã hóa và nén lưu file ảnh tạm vào bộ nhớ Cache
    private String saveImage(Bitmap bitmap) {
        try {
            java.io.File file = new java.io.File(requireContext().getCacheDir(),
                    "img_" + System.currentTimeMillis() + ".jpg");
            java.io.FileOutputStream out = new java.io.FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}