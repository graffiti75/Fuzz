package com.example.rodrigo.fuzz.service;

import com.example.rodrigo.fuzz.model.Fuzz;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Rodrigo Cericatto on 22/05/2015.
 */
public interface RetrofitService {
	@GET("/quizzes/mobile/1/data.json")
	void listFuzz(Callback<List<Fuzz>> callback);
}