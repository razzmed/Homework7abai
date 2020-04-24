package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<String> arrayList;

    public Adapter() {
        arrayList = new ArrayList<>();
    }

    public void update(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_holder, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}


