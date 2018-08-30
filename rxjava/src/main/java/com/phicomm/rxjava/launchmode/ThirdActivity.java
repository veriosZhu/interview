package com.phicomm.rxjava.launchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.phicomm.rxjava.R;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_first:
                startActivity(new Intent(this, FirstActivity.class));
                break;
            case R.id.btn_second:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.btn_third:
                startActivity(new Intent(this, ThirdActivity.class));
                break;
            case R.id.btn_fourth:
                startActivity(new Intent(this, FourthActivity.class));
                break;
            default:
        }
    }
}
