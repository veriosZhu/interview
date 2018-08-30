package com.phicomm.rxjava.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.phicomm.rxjava.R;
import com.phicomm.rxjava.listview.Fruit;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private FruitAdapter mFruitAdapter;

    private List<Fruit> mFruitList = new ArrayList<>(16);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        initFruit();
        LinearLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        mFruitAdapter = new FruitAdapter(mFruitList);
        recyclerView.setAdapter(mFruitAdapter);
    }

    private void initFruit() {
        for (int i = 0; i < 5; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            mFruitList.add(apple);
            mFruitList.add(banana);
            mFruitList.add(cherry);
            mFruitList.add(orange);
            mFruitList.add(pineapple);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_view_menu, menu);
        return true;
    }


    private int changePos = 2;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_part_update:
                mFruitList.get(changePos).setName("Watermelon");
                mFruitList.get(changePos).setImageId(R.drawable.watermelon_pic);
                mFruitAdapter.notifyItemChanged(changePos++);
                break;
            default:
        }
        return true;
    }
}
