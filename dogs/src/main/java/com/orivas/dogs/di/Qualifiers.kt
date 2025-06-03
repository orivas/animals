package com.orivas.dogs.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GetDogsUseCaseQ

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSourceQ

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSourceQ