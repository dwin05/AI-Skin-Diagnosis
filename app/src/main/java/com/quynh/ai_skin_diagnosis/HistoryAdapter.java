package com.quynh.ai_skin_diagnosis;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<HistoryItem> list;

    public HistoryAdapter(List<HistoryItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HistoryItem item = list.get(position);

        holder.txtDisease.setText(item.getResult());

        holder.txtDate.setText(item.getDate());

        holder.imgHistory.setImageBitmap(
                BitmapFactory.decodeFile(item.getImagePath())
        );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHistory;
        TextView txtDisease, txtDate;

        public ViewHolder(View itemView) {
            super(itemView);

            imgHistory = itemView.findViewById(R.id.imgHistory);
            txtDisease = itemView.findViewById(R.id.txtDisease);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}