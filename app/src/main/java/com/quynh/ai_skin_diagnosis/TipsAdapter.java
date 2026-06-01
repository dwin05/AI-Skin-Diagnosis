package com.quynh.ai_skin_diagnosis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    Context context;
    List<TipItem> list;

    public TipsAdapter(Context context, List<TipItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_tip, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TipItem item = list.get(position);

        holder.imgTip.setImageResource(item.getImageRes());
        holder.txtTitle.setText(item.getTitle());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, TipsDetailActivity.class);

            intent.putExtra("title", item.getTitle());
            intent.putExtra("content", item.getContent());
            intent.putExtra("image", item.getImageRes());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTip;
        TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imgTip = itemView.findViewById(R.id.imgTip);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}