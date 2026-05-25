package com.quynh.ai_skin_diagnosis;

import java.util.List;

public class SectionItem {
    private String sectionTitle;
    private List<ArticleItem> articleList;

    public SectionItem(String sectionTitle, List<ArticleItem> articleList) {
        this.sectionTitle = sectionTitle;
        this.articleList = articleList;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public List<ArticleItem> getArticleList() {
        return articleList;
    }
}
