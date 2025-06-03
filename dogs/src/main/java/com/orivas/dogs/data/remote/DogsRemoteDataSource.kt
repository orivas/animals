package com.orivas.dogs.data.remote

import android.util.Log
import com.orivas.dogs.data.DogsDataSource
import com.orivas.dogs.data.mappers.toDomain
import com.orivas.dogs.data.service.DogsService
import com.orivas.dogs.domain.model.DogsDto
import com.orivas.shared.domain.NetworkResult
import com.orivas.shared.domain.exceptions.UnknownResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogsRemoteDataSource @Inject constructor(
    private val dogsService: DogsService
) : DogsDataSource {

    override suspend fun getAllDogs(): Flow<NetworkResult<DogsDto>> = flow {
        with(dogsService.getAllDogs()) {
            if (isSuccessful) {
                this.body()?.let {
                    emit(
                        NetworkResult.OnSuccess(
                            data = DogsDto(
                                it.map { it.toDomain() }
                            ))
                    )
                } ?: emit(NetworkResult.OnError(UnknownResponseException()))

            } else {
                emit(NetworkResult.OnError(Exception(this.errorBody()?.string())))
            }
        }
    }.catch {
        emit(NetworkResult.OnError(Exception(it.localizedMessage)))
    }
}