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
                .inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryItem item = list.get(position);
        // Hiển thị kết quả
        holder.txtDisease.setText("Kết quả: " + item.getResult());
        // Hiển thị phần trăm
        holder.txtPercent.setText("Độ chính xác: " + String.format("%.0f%%", item.getPercent()));
        // ngày
        holder.txtDate.setText(item.getDate());
        // ảnh
        holder.imgHistory.setImageBitmap(BitmapFactory.decodeFile(item.getImagePath()));
        // Đổi màu theo kết quả
        if (item.getResult().equals("Lành tính")) {
            holder.txtDisease.setTextColor(
                    android.graphics.Color.parseColor("#2E7D32"));
        } else {
            holder.txtDisease.setTextColor(
                    android.graphics.Color.parseColor("#C62828")
            );
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHistory;
        TextView txtDisease, txtDate, txtPercent;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHistory = itemView.findViewById(R.id.imgHistory);
            txtDisease = itemView.findViewById(R.id.txtDisease);
            txtPercent = itemView.findViewById(R.id.txtPercent);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}