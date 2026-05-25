package com.quynh.ai_skin_diagnosis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private List<SectionItem> sectionList;

    public SectionAdapter(List<SectionItem> sectionList) {
        this.sectionList = sectionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_section, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SectionItem section = sectionList.get(position);

        holder.txtSectionTitle.setText(section.getSectionTitle());

        holder.recyclerHorizontal.setLayoutManager(
                new LinearLayoutManager(
                        holder.itemView.getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                )
        );
        holder.recyclerHorizontal.setAdapter(
                new ArticleAdapter(holder.itemView.getContext(), section.getArticleList()));
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtSectionTitle;
        RecyclerView recyclerHorizontal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSectionTitle = itemView.findViewById(R.id.txtSectionTitle);
            recyclerHorizontal = itemView.findViewById(R.id.recyclerHorizontal);
        }
    }
}