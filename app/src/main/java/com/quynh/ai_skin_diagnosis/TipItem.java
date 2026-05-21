package com.quynh.ai_skin_diagnosis;
public class TipItem {
    private int imageRes;
    private String title;
    private String content;

    public TipItem(int imageRes, String title, String content) {
        this.imageRes = imageRes;
        this.title = title;
        this.content = content;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
