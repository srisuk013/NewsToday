package com.srisuk.newstoday.models;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
  @GET("/v2/top-headlines?country=th&apiKey=41d2725710ae481795ff89337da615fe")
  Call<NewResponse> fetchNews();
}
