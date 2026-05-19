package com.quynh.ai_skin_diagnosis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<HistoryItem> list;
    HistoryAdapter adapter;
    public HistoryFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_history_fragment,
                                      container, false);
        recyclerView = view.findViewById(R.id.recyclerHistory);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );
        list = HistoryManager.loadHistory(
                requireContext()
        );

        adapter = new HistoryAdapter(list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}