package com.phicomm.rxjava.listview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.phicomm.rxjava.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private List<Fruit> mFruitList = new ArrayList<>(16);
    FruitAdapter fruitAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = findViewById(R.id.list_view);
        initFruit();
        fruitAdapter = new FruitAdapter(this, R.layout.fruit_item, mFruitList);
        listView.setAdapter(fruitAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(ListViewActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruit() {
        for (int i = 0; i < 3; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            Fruit banana  = new Fruit("Banana", R.drawable.banana_pic);
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
                fruitAdapter.updateItemView(listView, changePos++);
                fruitAdapter.notifyDataSetChanged();
                break;
                default:
        }
        return true;
    }
}
