package com.phicomm.rxjava.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phicomm.rxjava.R;
import com.phicomm.rxjava.listview.Fruit;

import java.util.List;

/**
 * @author qinxiang.zhu on 2018/8/2.
 * Copyright (C) 2018 Phicomm.
 */
class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private static final String TAG = "FruitAdapter";

    private List<Fruit> mFruitList;

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "create ViewHolder: " + getItemCount() + ", child count: ");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "on BindViewHolder: " + getItemCount());
        Fruit fruit = mFruitList.get(i);
        viewHolder.fruitName.setText(fruit.getName());
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);
        }
    }
}
