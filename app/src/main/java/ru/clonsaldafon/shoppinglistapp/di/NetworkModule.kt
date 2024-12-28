package ru.clonsaldafon.shoppinglistapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.clonsaldafon.shoppinglistapp.data.service.UserService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    private const val BASE_URL = ""
//
//    private val retrofit = Retrofit
//            .Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideUserService(): UserService =
//        retrofit.create(UserService::class.java)

}