package com.phicomm.service;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String url = "http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";
    private static final String TAG = "MainActivity";
    private static final String gifUrl = "http://149421.s21i.faiusr.com/3/ABUIABADGAAglrmW0wUopduX3AQwoAY4qAM.gif";
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.iv_glide);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_foreService:
                startService(new Intent(this, ForeService.class));
                break;
            case R.id.btn_glide:
                loadImageSimpleTarget();
                break;
                default:
        }

    }

    private void glide() {
        Glide.with(this)
                .load(url)
                .skipMemoryCache(true)
                .priority(Priority.HIGH)
                .thumbnail( 0.2f )
                .centerCrop()
                .into(mImageView);
    }

    private void loadGif() {
        Glide.with(this)
                .load(gifUrl)
                .asGif()
                .error(R.mipmap.ic_launcher)
                .into(mImageView);
    }

    private SimpleTarget<Bitmap> mSimpleTarget = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> animation) {
            mImageView.setImageBitmap(resource);
        }
    };

    private void loadImageSimpleTarget() {
        Glide.with( this)
                .load( url )
                .asBitmap()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .animate(animator)
                .into( mSimpleTarget );
    }

    ViewPropertyAnimation.Animator animator = new ViewPropertyAnimation.Animator() {
        @Override
        public void animate(View view) {
            view.setAlpha( 0f );

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat( view, "alpha", 0f, 1f );
            fadeAnim.setDuration( 2500 );
            fadeAnim.start();
        }
    };
}
