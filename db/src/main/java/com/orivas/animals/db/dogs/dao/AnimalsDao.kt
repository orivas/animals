package com.orivas.animals.db.dogs.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.orivas.animals.db.dogs.entities.Animal

@Dao
interface AnimalsDao {

    @Query("Select * From animal")
    suspend fun getAll(): List<Animal>?

    @Insert
    @JvmSuppressWildcards
    fun createAll(objects: List<Animal>)

}