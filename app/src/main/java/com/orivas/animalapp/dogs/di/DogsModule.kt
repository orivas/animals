package com.orivas.animalapp.dogs.di

import com.orivas.animalapp.dogs.presentation.viewmodel.DogsViewModel
import com.orivas.animals.db.AnimalDatabase
import com.orivas.animals.db.dogs.dao.AnimalsDao
import com.orivas.dogs.data.DogsDataSource
import com.orivas.dogs.data.DogsRepositoryImpl
import com.orivas.dogs.data.local.DogsLocalDataSource
import com.orivas.dogs.data.remote.DogsRemoteDataSource
import com.orivas.dogs.data.service.DogsService
import com.orivas.dogs.di.GetDogsUseCaseQ
import com.orivas.dogs.di.LocalDataSourceQ
import com.orivas.dogs.di.RemoteDataSourceQ
import com.orivas.dogs.domain.model.DogsDto
import com.orivas.dogs.domain.repository.DogsRepository
import com.orivas.dogs.domain.usecase.GetDogsUseCase
import com.orivas.shared.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object DogsModule {

    @Provides
    fun provideDogsService(retrofit: Retrofit): DogsService {
        return retrofit.create(DogsService::class.java)
    }

    @RemoteDataSourceQ
    @Provides
    fun provideDogsDataSource(dogsService: DogsService): DogsDataSource {
        return DogsRemoteDataSource(
            dogsService
        )
    }

    @Provides
    fun provideAnimalsDao(
        animalsDatabase: AnimalDatabase
    ): AnimalsDao =
        animalsDatabase.getAnimalsDao()

    @LocalDataSourceQ
    @Provides
    fun provideDogsLocalDataSource(animalsDao: AnimalsDao): DogsDataSource {
        return DogsLocalDataSource(
            animalsDao
        )
    }

    @Provides
    fun provideDogsRepository(
        @RemoteDataSourceQ
        remoteDataSource: DogsDataSource,
        @LocalDataSourceQ
        localDataSource: DogsDataSource
    ): DogsRepository {
        return DogsRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    @GetDogsUseCaseQ
    @Provides
    fun provideGetAllDogsUseCase(dogsRepository: DogsRepository):
            UseCase<Nothing, DogsDto> {
        return GetDogsUseCase(
            dogsRepository
        )
    }

    @Provides
    fun provideDogsViewModel(
        @GetDogsUseCaseQ dogsUseCase: UseCase<Nothing, DogsDto>
    ): DogsViewModel {
        return DogsViewModel(
            dogsUseCase
        )
    }

}