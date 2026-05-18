package com.quynh.ai_skin_diagnosis;

public class HistoryItem {

    private String imagePath;
    private String result;
    private String date;

    public HistoryItem(String imagePath, String result, String date) {
        this.imagePath = imagePath;
        this.result = result;
        this.date = date;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getResult() {
        return result;
    }

    public String getDate() {
        return date;
    }
}