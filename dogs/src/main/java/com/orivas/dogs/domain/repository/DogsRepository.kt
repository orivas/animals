package com.orivas.dogs.domain.repository

import com.orivas.dogs.domain.model.DogsDto
import com.orivas.shared.domain.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DogsRepository {

    suspend fun getAllDogs(): Flow<NetworkResult<DogsDto>>

}