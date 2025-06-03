package com.orivas.dogs.domain.usecase

import com.orivas.dogs.domain.model.DogsDto
import com.orivas.dogs.domain.repository.DogsRepository
import com.orivas.shared.domain.NetworkResult
import com.orivas.shared.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(
    private val dogsRepository: DogsRepository
) : UseCase<Nothing, DogsDto> {

    override suspend fun execute(params: Nothing?): Flow<NetworkResult<DogsDto>> {
        return dogsRepository.getAllDogs()
    }
}