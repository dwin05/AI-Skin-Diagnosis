package com.quynh.ai_skin_diagnosis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ArticleFragment extends Fragment {

    RecyclerView recyclerSection;

    public ArticleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_article_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        recyclerSection = view.findViewById(R.id.recyclerSection);

        recyclerSection.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        List<SectionItem> sectionList = new ArrayList<>();

        // SECTION 1
        List<ArticleItem> chronicList = new ArrayList<>();

        chronicList.add(new ArticleItem(
                R.drawable.benhda,
                "Lang ben",
                "https://www.vinmec.com/vie/bai-viet/trieu-chung-nam-lang-ben-va-cach-dieu-tri-vi"));

        chronicList.add(new ArticleItem(
                R.drawable.viemdacodia,
                "Viêm da cơ địa",
                "https://www.vinmec.com/vie/bai-viet/lam-nao-khi-bi-viem-da-co-dia-man-tinh-tai-phat-nhieu-lan-vi"));

        chronicList.add(new ArticleItem(
                R.drawable.vaynen,
                "Vảy nến",
                "https://www.vinmec.com/vie/bai-viet/vay-nen-la-gi-tat-ca-nhung-dieu-ban-can-biet-ve-benh-vay-nen-vi"));

        sectionList.add(new SectionItem(
                "Bệnh Da Mãn Tính",
                chronicList
        ));


// SECTION 2
        List<ArticleItem> cancerList = new ArrayList<>();

        cancerList.add(new ArticleItem(
                R.drawable.actinh,
                "Ung thư da là gì?",
                "https://www.vinmec.com/vie/benh/ung-thu-da-4452"));

        cancerList.add(new ArticleItem(
                R.drawable.hacto,
                "Ung thư thể tăng sắc tố",
                "https://tamanhhospital.vn/benh/ung-thu-hac-to-da/"
        ));

        cancerList.add(new ArticleItem(
                R.drawable.tbday,
                "Ung thư tế bào đáy",
                "https://tamanhhospital.vn/ung-thu-bieu-mo-te-bao-day/"
        ));

        cancerList.add(new ArticleItem(
                R.drawable.vay,
                "Ung thư tế bào vảy",
                "https://dalieu.vn/ung-thu-te-bao-vay-d3300.html"
        ));

        sectionList.add(new SectionItem(
                "Bệnh Da Ác Tính",
                cancerList
        ));


// SECTION 3
        List<ArticleItem> aiList = new ArrayList<>();

        aiList.add(new ArticleItem(
                R.drawable.ai1,
                "AI hoạt động thế nào",
                "https://vnexpress.net/ai-hoc-con-nguoi-the-nao-3670916.html"
        ));

        aiList.add(new ArticleItem(
                R.drawable.ai2,
                "Độ chính xác",
                "https://unu-edu.translate.goog/article/never-assume-accuracy-artificial-intelligence-information-equals-truth?_x_tr_sl=en&_x_tr_tl=vi&_x_tr_hl=vi&_x_tr_pto=tc"
        ));

        aiList.add(new ArticleItem(
                R.drawable.ai3,
                "Lưu ý khi chẩn đoán",
                "https://baotintuc.vn/y-te/rui-ro-khi-tu-chan-doan-benh-bang-bac-si-ai-20251120162144282.htm"
        ));

        sectionList.add(new SectionItem(
                "AI Nhận Diện Da",
                aiList
        ));

        recyclerSection.setAdapter(
                new SectionAdapter(sectionList)
        );
    }
}