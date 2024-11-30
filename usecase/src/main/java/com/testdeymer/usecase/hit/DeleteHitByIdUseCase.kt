package com.testdeymer.usecase.hit

import com.testdeymer.repository.repositories.IHitRepository
import javax.inject.Inject

class DeleteHitByIdUseCase @Inject constructor(
    private val hitRepository: IHitRepository
) {

    fun invoke(
        objectId: String
    ) = hitRepository.deleteHitById(
        objectId
    )
}