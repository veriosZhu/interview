package com.phicomm.interview;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                /*Intent intent = new Intent();
                //第一种方式
                // ComponentName cn = new ComponentName("com.phicomm.rxjava", "com.phicomm.rxjava.launchmode.FirstActivity");
                // intent.setComponent(cn);
                //第二种方式
                intent.setClassName("com.phicomm.rxjava", "com.phicomm.rxjava.launchmode.FirstActivity");

                try {
                    startActivity(intent);
                } catch (Exception e) {
                    //TODO  可以在这里提示用户没有安装应用或找不到指定Activity，或者是做其他的操作
                }*/

                mTextView.setText("sssssssss");

                break;
                default:
        }
    }
}
