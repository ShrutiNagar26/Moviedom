package com.example.moviedom.network

import com.example.moviedom.data.MovieApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetwork {
    companion object {
        private var BASE_URL = "http://www.omdbapi.com"

        private fun retrofit(): Retrofit {

            var mHttpLoginInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttp = OkHttpClient.Builder()
                .addInterceptor(mHttpLoginInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getMovieAPI(): MovieApi{
            return retrofit().create(MovieApi::class.java)
        }

    }
}