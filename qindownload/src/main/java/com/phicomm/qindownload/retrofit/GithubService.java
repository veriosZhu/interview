package com.phicomm.qindownload.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author qinxiang.zhu on 2018/8/21.
 * Copyright (C) 2018 Phicomm.
 */
public interface GithubService {
    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Repo>> getUserRepos(@Path("user") String user);

    @GET("users/{user}/following")
    Call<List<User>> getUserFollowing(@Path("user") String user);

    // Using RxJava
    @GET("users/{user}")
    Observable<User> getUserObservable(@Path("user") String user);

    @GET("users/{user}/repos")
    Observable<List<Repo>> getUserReposObservable(@Path("user") String user);

    @GET("users/{user}/following")
    Observable<List<User>> getUserFollowingObservable(@Path("user") String user);
}
