package com.phicomm.rxjava.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.phicomm.rxjava.R;

import java.util.List;

/**
 * @author qinxiang.zhu on 2018/8/2.
 * Copyright (C) 2018 Phicomm.
 */
class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;
    private List<Fruit> mFruitList;

    public FruitAdapter(Context context, int textViewResourceId,
                        List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        mFruitList = objects;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById (R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById (R.id.fruit_name);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {

        ImageView fruitImage;

        TextView fruitName;

    }

    public void updateItemView(ListView listView, int position) {
        //换算成 Item View 在 ViewGroup 中的 index
        int index = position - listView.getFirstVisiblePosition();
        if (index >= 0 && index < listView.getChildCount()) {
            //更新数据
            Fruit fruit = mFruitList.get(position);
            fruit.setName("Pear");
            fruit.setImageId(R.drawable.pear_pic);
            //更新单个Item
            View itemView = listView.getChildAt(index);
            getView(position, itemView, listView);
        }
    }

}