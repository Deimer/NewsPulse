package com.testdeymer.datasource.di

import com.testdeymer.datasource.remote.HitRemoteDataSource
import com.testdeymer.datasource.remote.IHitRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindHitRemoteDataSource(
        implRemoteDataSourceImpl: HitRemoteDataSource
    ): IHitRemoteDataSource
}