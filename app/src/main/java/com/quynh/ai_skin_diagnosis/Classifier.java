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

    private MappedByteBuffer loadModelFile(
            Context context
    ) throws IOException {

        AssetFileDescriptor fileDescriptor =
                context.getAssets().openFd(
                        "skin_disease_model.tflite"
                );

        FileInputStream inputStream =
                new FileInputStream(
                        fileDescriptor.getFileDescriptor()
                );

        FileChannel fileChannel =
                inputStream.getChannel();

        long startOffset =
                fileDescriptor.getStartOffset();

        long declaredLength =
                fileDescriptor.getDeclaredLength();

        return fileChannel.map(
                FileChannel.MapMode.READ_ONLY,
                startOffset,
                declaredLength
        );
    }

    public String predict(Bitmap bitmap) {

        Bitmap resizedBitmap =
                Bitmap.createScaledBitmap(
                        bitmap,
                        IMAGE_SIZE,
                        IMAGE_SIZE,
                        true
                );

        ByteBuffer byteBuffer =
                ByteBuffer.allocateDirect(
                        4 * IMAGE_SIZE * IMAGE_SIZE * 3
                );

        byteBuffer.order(ByteOrder.nativeOrder());

        int[] intValues =
                new int[IMAGE_SIZE * IMAGE_SIZE];

        resizedBitmap.getPixels(
                intValues,
                0,
                IMAGE_SIZE,
                0,
                0,
                IMAGE_SIZE,
                IMAGE_SIZE
        );

        int pixel = 0;

        for (int i = 0; i < IMAGE_SIZE; i++) {

            for (int j = 0; j < IMAGE_SIZE; j++) {

                int val = intValues[pixel++];

                float r =
                        (((val >> 16) & 0xFF)
                                - 127.5f) / 127.5f;

                float g =
                        (((val >> 8) & 0xFF)
                                - 127.5f) / 127.5f;

                float b =
                        ((val & 0xFF)
                                - 127.5f) / 127.5f;

                byteBuffer.putFloat(r);
                byteBuffer.putFloat(g);
                byteBuffer.putFloat(b);
            }
        }

        float[][] output = new float[1][1];

        interpreter.run(byteBuffer, output);

        float confidence = output[0][0];

        if (confidence > 0.5f) {
            return "Ác tính";
        } else {
            return "Lành tính";
        }
    }

    public void close() {

        if (interpreter != null) {
            interpreter.close();
        }
    }
}