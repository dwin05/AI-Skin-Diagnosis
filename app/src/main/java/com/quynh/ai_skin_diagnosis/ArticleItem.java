package com.quynh.ai_skin_diagnosis;

public class ArticleItem {
    private int imageRes;
    private String title;
    private String url;

    public ArticleItem(int imageRes, String title, String url) {
        this.imageRes = imageRes;
        this.title = title;
        this.url = url;
    }
    public int getImageRes() {
        return imageRes;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
