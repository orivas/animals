package com.orivas.animalapp.di

import android.content.Context
import androidx.room.Room
import com.orivas.animals.db.AnimalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimalsAppModule {

    private const val BASE = "https://jsonblob.com/api/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder().apply {
                    readTimeout(15, TimeUnit.SECONDS)
                    connectTimeout(15, TimeUnit.SECONDS)
                }.build()
            ).build()
    }

    @Provides
    @Singleton
    fun provideAnimalsDatabase(
        @ApplicationContext context: Context
    ): AnimalDatabase {
        return Room.databaseBuilder(
            context,
            AnimalDatabase::class.java,
            "animals.db"
        ).build()
    }

}