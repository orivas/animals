package com.orivas.dogs.data.mappers

import com.orivas.animals.db.dogs.entities.Animal
import com.orivas.dogs.data.model.DogResponse
import com.orivas.dogs.domain.model.DogDto

const val AGE = "Almost %s years"


fun DogResponse.toDomain(): DogDto {
    return DogDto(
        dogName = this.dogName,
        description = this.description,
        age = AGE.format(this.age),
        image = this.image
    )
}

fun DogDto.toEntity(): Animal {
    return Animal(
        id = 0,
        dogName = this.dogName,
        description = this.description,
        age = age,
        image = this.image
    )
}

fun Animal.toDomain(): DogDto {
    return DogDto(
        dogName = this.dogName,
        description = this.description,
        age = this.age,
        image = this.image
    )
}