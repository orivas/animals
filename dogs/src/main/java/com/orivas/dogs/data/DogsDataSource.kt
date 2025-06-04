package com.orivas.dogs.data

import com.orivas.dogs.domain.model.DogDto
import com.orivas.dogs.domain.model.DogsDto
import com.orivas.shared.domain.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DogsDataSource {

    suspend fun getAllDogs(): Flow<NetworkResult<DogsDto>>

}

interface SaveDataLocalSource {
    suspend fun saveAllDogs(list: List<DogDto>)
}

interface LocalDataSource: DogsDataSource, SaveDataLocalSource