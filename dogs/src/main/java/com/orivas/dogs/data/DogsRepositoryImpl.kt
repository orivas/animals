package com.orivas.dogs.data

import com.orivas.dogs.di.LocalDataSourceQ
import com.orivas.dogs.di.RemoteDataSourceQ
import com.orivas.dogs.domain.model.DataFrom
import com.orivas.dogs.domain.model.DogDto
import com.orivas.dogs.domain.model.DogsDto
import com.orivas.dogs.domain.repository.DogsRepository
import com.orivas.shared.domain.NetworkResult
import com.orivas.shared.domain.exceptions.UnknownResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(
    @RemoteDataSourceQ
    private val remoteDataSource: DogsDataSource,
    @LocalDataSourceQ
    private val localDataSource: DogsDataSource,

    ) : DogsRepository {
    override suspend fun getAllDogs(): Flow<NetworkResult<DogsDto>> {
        return flow {
            emit(NetworkResult.Loading)

            //remove, only for test
            delay(1000L)
            when (val local = localDataSource.getAllDogs().last()) {
                is NetworkResult.OnSuccess -> {
                    if (local.data.dogs.isNotEmpty()) {
                        emit(
                            NetworkResult.OnSuccess(
                                DogsDto(dogs = local.data.dogs, dataFrom = DataFrom.Local)
                            )
                        )
                    } else {
                        emit(getRemoteData().last())
                    }
                }

                else -> {
                    emit(getRemoteData().last())
                }
            }
        }.flowOn(Dispatchers.IO)

    }

    private fun getRemoteData(): Flow<NetworkResult<DogsDto>> {
        return flow {
            when (val remote = remoteDataSource.getAllDogs().last()) {
                is NetworkResult.OnSuccess -> {
                    saveData(remote.data.dogs)
                    emit(
                        NetworkResult.OnSuccess(
                            DogsDto(dogs = remote.data.dogs)
                        )
                    )
                }

                else -> {
                    emit(NetworkResult.OnError(UnknownResponseException()))
                }
            }
        }
    }

    private suspend fun saveData(animals: List<DogDto>) {
        if (animals.isNotEmpty()) (localDataSource as SaveDataLocalSource).saveAllDogs(animals)
    }

}