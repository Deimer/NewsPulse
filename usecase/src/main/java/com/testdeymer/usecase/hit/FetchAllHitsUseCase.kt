package com.testdeymer.usecase.hit

import com.testdeymer.repository.repositories.IHitRepository
import javax.inject.Inject

class FetchAllHitsUseCase @Inject constructor(
    private val hitRepository: IHitRepository
) {

    fun invoke() = hitRepository.fetchAllHits()
}