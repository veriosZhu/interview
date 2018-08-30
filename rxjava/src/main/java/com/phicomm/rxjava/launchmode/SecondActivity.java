package com.phicomm.rxjava.launchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.phicomm.rxjava.R;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_first: {
                Intent intent = new Intent(this, FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_second: {
                Intent intent = new Intent(this, SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            }
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
