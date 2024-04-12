package com.example.moviedom.di

import com.example.moviedom.data.MovieApi
import com.example.moviedom.other.Constants
import com.example.moviedom.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseURl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient= OkHttpClient.Builder()
        .addInterceptor(provideLoggingInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun getMovieAPI(retrofit: Retrofit):MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(movieApI:MovieApi) = MovieRepository(movieApI)
}