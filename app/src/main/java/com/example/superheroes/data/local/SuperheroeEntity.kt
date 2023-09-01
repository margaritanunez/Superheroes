package com.example.superheroes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superheroe_table")
data class SuperheroeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val origin: String,
    val imageLink: String,
    val power: String,
    val yearCreation: Int
)
