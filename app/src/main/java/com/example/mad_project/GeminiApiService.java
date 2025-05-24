package com.example.mad_project;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GeminiApiService {
    @POST("v1beta/models/gemini-1.5-pro-latest:generateContent")
    Call<GeminiApiModels.GeminiResponse> generateContent(
        @Query("key") String apiKey,
        @Body GeminiApiModels.GeminiRequest request
    );
}