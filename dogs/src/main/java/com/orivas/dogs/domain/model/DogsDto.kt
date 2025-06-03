package com.orivas.dogs.domain.model

enum class DataFrom { Local, Remote }

data class DogsDto(
    val dogs: List<DogDto>,
    val dataFrom: DataFrom = DataFrom.Remote
)

data class DogDto(
    val dogName: String,
    val description: String,
    val age: String,
    val image: String
)
