package dev.vladmakarenko.financialPlanning.core.di

import dagger.Module
import dagger.Provides
import dev.vladmakarenko.financialPlanning.core.repository.remote.NetworkService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object{
        const val BASE_URL = "http://139.59.159.43/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{

        val converterFactory = ScalarsConverterFactory.create()
        val gsonConverterFactory = GsonConverterFactory.create()
        val client = OkHttpClient
            .Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): NetworkService{
        return retrofit.create()
    }
}