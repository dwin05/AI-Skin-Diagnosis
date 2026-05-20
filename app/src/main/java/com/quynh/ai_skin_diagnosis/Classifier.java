package com.quynh.ai_skin_diagnosis;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Classifier {
    private Interpreter interpreter;
    private static final int IMAGE_SIZE = 224;
    public Classifier(Context context) throws IOException {
        interpreter = new Interpreter(
                loadModelFile(context)
        );
    }
    private MappedByteBuffer loadModelFile(Context context) throws IOException {
        AssetFileDescriptor fileDescriptor = context.getAssets().openFd("skin_disease_model.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
    public PredictionResult predict(Bitmap bitmap) {
        // resize anh 224x224 để ko bị crash
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, IMAGE_SIZE, IMAGE_SIZE, true);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * IMAGE_SIZE * IMAGE_SIZE * 3);
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues = new int[IMAGE_SIZE * IMAGE_SIZE];
        resizedBitmap.getPixels(intValues, 0, IMAGE_SIZE, 0, 0, IMAGE_SIZE, IMAGE_SIZE);
        int pixel = 0;
        for (int i = 0; i < IMAGE_SIZE; i++) {
            for (int j = 0; j < IMAGE_SIZE; j++) {
                int val = intValues[pixel++];
                float r = (float) ((val >> 16) & 0xFF);
                float g = (float) ((val >> 8) & 0xFF);
                float b = (float) (val & 0xFF);
                byteBuffer.putFloat(r);
                byteBuffer.putFloat(g);
                byteBuffer.putFloat(b);
            }
        }
        // 2. Chạy mô hình để lấy kết quả xác suất trực tiếp từ lớp Sigmoid của TFLite
        float[][] output = new float[1][1];
        interpreter.run(byteBuffer, output);
        // Giá trị output[0][0] này đã chạy qua Sigmoid từ file .tflite nên mặc định nằm trong khoảng [0.0, 1.0]
        float malignant = output[0][0];
        float benign = 1f - malignant;
        String label;
        if (malignant > benign) {
            label = "Ác tính";
        } else {
            label = "Lành tính";
        }
        return new PredictionResult(
                label,
                benign * 100f,
                malignant * 100f
        );
    }
    public void close() {
        if (interpreter != null) {
            interpreter.close();
        }
    }
}