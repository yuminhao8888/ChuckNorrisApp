package com.example.chucknorrisapp.di

import com.example.chucknorrisapp.rest.JokesApi
import com.example.chucknorrisapp.rest.JokesRepository
import com.example.chucknorrisapp.rest.JokesRepositoryImpl
import com.example.chucknorrisapp.rest.RequestInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun providesGson() = Gson()

    @Provides
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun providesRequestInterceptor() = RequestInterceptor()

    @Provides
    fun providesOkHttpClient(
        requestInterceptor: RequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesRetrofitService(okHttpClient: OkHttpClient, gson: Gson): JokesApi {
        return Retrofit.Builder()
            .baseUrl(JokesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

            .client(okHttpClient)
            .build()
            .create(JokesApi::class.java)
    }

    @Provides
    fun providesJokesRepository(jokesApi: JokesApi): JokesRepository {
        return JokesRepositoryImpl(jokesApi)
    }

    @Provides
    fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO
}