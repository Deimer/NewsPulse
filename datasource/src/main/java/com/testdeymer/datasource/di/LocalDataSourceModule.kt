package com.testdeymer.datasource.di

import com.testdeymer.datasource.local.HitLocalDataSource
import com.testdeymer.datasource.local.IHitLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindHitLocalDataSource(
        implLocalDataSourceImpl: HitLocalDataSource
    ): IHitLocalDataSource
}