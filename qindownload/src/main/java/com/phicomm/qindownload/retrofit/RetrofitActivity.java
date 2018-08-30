package com.phicomm.qindownload.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.phicomm.qindownload.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    GithubService mService;
    TextView mTextView;
    public static final String USER_NAME = "veriosZhu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        mService = createRetrofit();
        mTextView = findViewById(R.id.tv_result);
    }

    private GithubService createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GithubService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:
                Call<User> user = mService.getUser(USER_NAME);
                user.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        mTextView.setText(response.body().getEmail());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
                break;
            case R.id.btn_observable:
                mService.getUserObservable(USER_NAME)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
                                mTextView.setText(user.getEmail());
                            }
                        });
                break;
                default:
        }
    }
}
