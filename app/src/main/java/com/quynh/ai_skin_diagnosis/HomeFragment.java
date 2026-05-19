package com.quynh.ai_skin_diagnosis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ImageView imgView;
    Button btnCamera, btnGallery, btnAnalyze;
    TextView txtResult, txtBenign, txtMalignant;
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
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgView = view.findViewById(R.id.imgView);
        btnCamera = view.findViewById(R.id.btnCamera);
        btnGallery = view.findViewById(R.id.btnGallery);
        btnAnalyze = view.findViewById(R.id.btnAnalyze);
        txtResult = view.findViewById(R.id.txtResult);
        txtBenign = view.findViewById(R.id.txtBenign);
        txtMalignant = view.findViewById(R.id.txtMalignant);
        //tải model AI lên
        try {
            classifier = new Classifier(requireContext());
        } catch (IOException e) {
            e.printStackTrace();
            txtResult.setText("Lỗi tải AI model!");
        }
        // CAMERA
        btnCamera.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(intent);
        });
        // GALLERY
        btnGallery.setOnClickListener(v -> {Intent intent = new Intent(
                    Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

    // CAMERA RESULT
    ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() ==
                                getActivity().RESULT_OK
                                && result.getData() != null) {
                            Bundle extras = result.getData().getExtras();
                            if (extras != null) {
                                bitmap = (Bitmap) extras.get("data");
                                imgView.setImageBitmap(bitmap);
                            }
                        }
                    });
    // GALLERY RESULT
    ActivityResultLauncher<Intent> galleryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() ==
                                getActivity().RESULT_OK
                                && result.getData() != null) {
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(
                                        requireActivity().getContentResolver(),
                                        result.getData().getData()
                                );
                                imgView.setImageBitmap(bitmap);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
    // lưu lại bằng sharePreferences
    SharedPreferences preferences = requireContext().getSharedPreferences("HistoryData", Context.MODE_PRIVATE);

    // AI PREDICT
    private void predictImage(Bitmap bitmap) {
        PredictionResult result = classifier.predict(bitmap);
        txtResult.setText("Kết quả: " + result.getLabel());
        txtBenign.setText("Lành tính: " + (int) result.getBenignPercent() + "%");
        txtMalignant.setText("Ác tính: " + (int) result.getMalignantPercent() + "%");
        // load history cũ
        ArrayList<HistoryItem> historyList = HistoryManager.loadHistory(requireContext());
        // lưu ảnh
        String imagePath = saveImage(bitmap);
        // thời gian
        String currentDate = java.text.DateFormat.getDateTimeInstance()
                             .format(new java.util.Date());
        // thêm vào list history
        float percent;
        if (result.getLabel().equals("Lành tính")) {
            percent = result.getBenignPercent();
        } else {
            percent = result.getMalignantPercent();
        }
        historyList.add(0, new HistoryItem(imagePath, result.getLabel(),
                                                percent, currentDate));
        HistoryManager.saveHistory(requireContext(), historyList);
    }
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