package com.orivas.shared.domain.usecase

import com.orivas.shared.domain.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UseCase<Params, T> {
    suspend fun execute(params: Params? = null): Flow<NetworkResult<T>>
}