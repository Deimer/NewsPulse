package com.testdeymer.usecase.hit

import com.testdeymer.repository.repositories.IHitRepository
import javax.inject.Inject

class FetchHitByIdUseCase @Inject constructor(
    private val hitRepository: IHitRepository
) {

    operator fun invoke(
        objectId: String
    ) = hitRepository.getHitById(
        objectId
    )
}