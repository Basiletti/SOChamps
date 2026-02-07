package com.basiletti.gino.sochamps.di

import com.basiletti.gino.sochamps.common.Constants.BASE_URL
import com.basiletti.gino.sochamps.data.remote.StackOverflowApi
import com.basiletti.gino.sochamps.data.repository.StackOverflowUsersRepositoryImpl
import com.basiletti.gino.sochamps.domain.mapper.UserMapper
import com.basiletti.gino.sochamps.domain.mapper.UserMapperImpl
import com.basiletti.gino.sochamps.domain.repository.StackOverflowUsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideStackOverflowApi(client: OkHttpClient): StackOverflowApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideUserMapper(): UserMapper {
        return UserMapperImpl()
    }


    @Provides
    @Singleton
    fun provideStackOverflowUsersRepository(
        api: StackOverflowApi,
        mapper: UserMapper,
    ): StackOverflowUsersRepository {
        return StackOverflowUsersRepositoryImpl(
            api = api,
            mapper = mapper
        )
    }

}