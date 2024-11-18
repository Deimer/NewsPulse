package com.testdeymer.newspulse.di

import com.testdeymer.repository.repositories.IHitRepository
import com.testdeymer.usecase.hit.DeleteHitByIdUseCase
import com.testdeymer.usecase.hit.FetchAllHitsUseCase
import com.testdeymer.usecase.hit.FetchHitByIdUseCase
import com.testdeymer.usecase.hit.GetHitsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetHitsUseCase(
        hitRepository: IHitRepository
    ) = GetHitsUseCase(hitRepository)

    @Provides
    @ViewModelScoped
    fun provideFetchHitByIdUseCase(
        hitRepository: IHitRepository
    ) = FetchHitByIdUseCase(hitRepository)

    @Provides
    @ViewModelScoped
    fun provideFetchAllHitsUseCase(
        hitRepository: IHitRepository
    ) = FetchAllHitsUseCase(hitRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteHitByIdUseCase(
        hitRepository: IHitRepository
    ) = DeleteHitByIdUseCase(hitRepository)
}