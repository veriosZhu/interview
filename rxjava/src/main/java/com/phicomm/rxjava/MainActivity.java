package com.phicomm.rxjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.phicomm.rxjava.launchmode.FirstActivity;
import com.phicomm.rxjava.launchmode.ThirdActivity;
import com.phicomm.rxjava.listview.ListViewActivity;
import com.phicomm.rxjava.recyclerview.RecyclerViewActivity;
import com.phicomm.rxjava.rxjava.RxjavaActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mtvLog;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtvLog = findViewById(R.id.text);
        Activity
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rxjava:
                startActivity(new Intent(this, RxjavaActivity.class));
                break;
            case R.id.btn_listview:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.btn_recyclerview:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btn_launch_mode:
                Intent intent = new Intent(this, FirstActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            default:
        }
    }

}
