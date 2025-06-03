package com.orivas.animals.db.dogs.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "animal")
data class Animal(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("animal_name")
    val dogName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("age")
    val age: String,
    @SerializedName("image")
    val image: String,
)