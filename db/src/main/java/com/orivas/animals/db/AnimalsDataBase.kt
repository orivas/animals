package com.orivas.animals.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orivas.animals.db.dogs.dao.AnimalsDao
import com.orivas.animals.db.dogs.entities.Animal

@Database(version = 1, entities = [Animal::class], exportSchema = false)
abstract class AnimalDatabase : RoomDatabase() {
    abstract fun getAnimalsDao(): AnimalsDao
}