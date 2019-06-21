package com.example.marvelrecyclerveiw;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiActivity {


        String BASE_URL = "https://simplifiedcoding.net/demos/";

        @GET("marvel")
        Call<List<HeroActivity>> getHeroes();
    }
