package com.afs.retrofit.api;

import com.afs.retrofit.bean.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<UserInfo>> listRepos(@Path("user") String user);
}
