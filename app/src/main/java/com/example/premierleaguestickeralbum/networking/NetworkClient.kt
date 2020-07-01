package com.example.premierleaguestickeralbum.networking

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Object that initializes Retrofit client
 */
object NetworkClient {
    private const val BASE_URL = "http://192.168.1.101:8080/"
    private var retrofit: Retrofit? = null

    /**
     * Singleton method returning Retrofit instance
     */
    fun getInstance(): Api? {
        if (retrofit == null) {
            var httpClient = OkHttpClient.Builder().addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .build()
                chain.proceed(newRequest)
            }.build()

            val gson = GsonBuilder().setLenient().create()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()
        }
        return retrofit?.create(Api::class.java)
    }
}