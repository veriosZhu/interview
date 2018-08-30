package com.phicomm.rxjava.rxjava;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.phicomm.rxjava.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxjavaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RxjavaActivity";

    private TextView mtvLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        mtvLog = (TextView) findViewById(R.id.tv_log);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_flowable:
                initData();
                break;
                default:
        }
    }

    private void doRxjava() {
        clear();
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("1");
                        emitter.onNext("2");
                        emitter.onNext("3");
                        emitter.onComplete();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        append("onSubscribe():  ");
                    }
                    @Override
                    public void onNext(String s) {
                        append("onNext():  " + s);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                        append("onComplete():  ");
                    }
                });
    }

    private void append(CharSequence str) {
        mtvLog.append(str + "\n");
    }

    private void clear() {
        mtvLog.setText("");
    }

    private void doRxjava2() {
        clear();
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("1");
                        emitter.onNext("r");
                    }
                })
                /*.flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just(s);
                    }
                })*/
                .subscribe(new Observer<String>() {
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }
                    @Override
                    public void onNext(String s) {
                        append(s);
                        Integer.parseInt(s);
//                        mDisposable.dispose();
                    }
                    @Override
                    public void onError(Throwable e) {
                        append("on Error");
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void doRxjavaThread() {
        new Thread() {
            @Override
            public void run() {
                Log.d(TAG, "Thread run() 所在线程为 :" + Thread.currentThread().getName());
                Observable
                        .create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                Log.d(TAG, "Observable subscribe() 所在线程为 :" + Thread.currentThread().getName());
                                emitter.onNext("文章1");
                                emitter.onNext("文章2");
                                emitter.onComplete();
                            }
                        })
                        .subscribeOn(Schedulers.single())
                        .map(new Function<String, String>() {
                            @Override
                            public String apply(String s) throws Exception {
                                Log.d(TAG, "Observable map 所在线程为 :" + Thread.currentThread().getName());
                                return s;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "Observer onSubscribe() 所在线程为 :" + Thread.currentThread().getName());
                            }

                            @Override
                            public void onNext(String s) {
                                Log.d(TAG, "Observer onNext() 所在线程为 :" + Thread.currentThread().getName());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "Observer onError() 所在线程为 :" + Thread.currentThread().getName());
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "Observer onComplete() 所在线程为 :" + Thread.currentThread().getName());
                            }
                        });
            }
        }.start();
    }

    private void doRxjavaFlowable() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                emitter.onNext("one");
                emitter.onNext("two");
                emitter.onNext("three");
                emitter.onNext("four");
                emitter.onNext("five");
                emitter.onComplete();
            }
        }, BackpressureStrategy.DROP)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError: " + t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    private void initData() {
        // 每隔1s执行一次事件
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Log.i("接收数据", String.valueOf(aLong));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
