package com.quynh.ai_skin_diagnosis;

public class HistoryItem {

    private String imagePath;
    private String result;
    private float percent;
    private String date;

    public HistoryItem(String imagePath, String result, float percent, String date) {
        this.imagePath = imagePath;
        this.result = result;
        this.percent = percent;
        this.date = date;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getResult() {
        return result;
    }

    public float getPercent() {
        return percent;
    }

    public String getDate() {
        return date;
    }
}