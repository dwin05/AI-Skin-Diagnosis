package com.quynh.ai_skin_diagnosis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<ArticleItem> list;
    private Context context;

    public ArticleAdapter(Context context, List<ArticleItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ArticleItem item = list.get(position);

        holder.txtTitle.setText(item.getTitle());
        holder.imgArticle.setImageResource(item.getImageRes());

        // CLICK ITEM -> OPEN WEBVIEW
        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context,
                    WebViewActivity.class);

            intent.putExtra("url", item.getUrl());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgArticle;
        TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgArticle = itemView.findViewById(R.id.imgArticle);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}