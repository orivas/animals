package com.orivas.dogs.data.local

import com.orivas.animals.db.dogs.dao.AnimalsDao
import com.orivas.dogs.data.DogsDataSource
import com.orivas.dogs.data.SaveDataLocalSource
import com.orivas.dogs.data.mappers.toDomain
import com.orivas.dogs.data.mappers.toEntity
import com.orivas.dogs.domain.model.DogDto
import com.orivas.dogs.domain.model.DogsDto
import com.orivas.shared.domain.NetworkResult
import com.orivas.shared.domain.exceptions.UnknownResponseException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogsLocalDataSource @Inject constructor(
    private val animalsDao: AnimalsDao
) : DogsDataSource, SaveDataLocalSource {

    override suspend fun getAllDogs(): Flow<NetworkResult<DogsDto>> {
        return flow {
            animalsDao.getAll()?.let {
                emit(
                    NetworkResult.OnSuccess(
                        DogsDto(
                            dogs = it.map { animal ->
                                animal.toDomain()
                            }
                        )
                    )
                )
            } ?: emit(NetworkResult.OnError(UnknownResponseException()))
        }.catch {
            emit(NetworkResult.OnError(UnknownResponseException()))
        }
    }

    override suspend fun saveAllDogs(list: List<DogDto>) {
        animalsDao.createAll(list.map {
            it.toEntity()
        })
    }
}