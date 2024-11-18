package com.testdeymer.usecase.hit

import com.testdeymer.repository.repositories.IHitRepository
import javax.inject.Inject

class GetHitsUseCase @Inject constructor(
    private val hitRepository: IHitRepository
) {

    suspend fun invoke() =
        hitRepository.getHits()
}