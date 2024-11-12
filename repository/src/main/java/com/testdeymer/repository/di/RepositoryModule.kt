package com.testdeymer.repository.di

import com.testdeymer.repository.repositories.HitRepository
import com.testdeymer.repository.repositories.IHitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHitRepository(
        implRepository: HitRepository
    ): IHitRepository
}