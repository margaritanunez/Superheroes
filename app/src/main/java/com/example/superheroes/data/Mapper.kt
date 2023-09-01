package com.example.superheroes.data

import com.example.superheroes.data.local.DetailSuperheroeEntity
import com.example.superheroes.data.local.SuperheroeEntity
import com.example.superheroes.data.remote.DetailSuperheroe
import com.example.superheroes.data.remote.Superheroe


fun Superheroe.transformToEntity(): SuperheroeEntity = SuperheroeEntity(
    this.id,
    this.name,
    this.origin,
    this.imageLink,
    this.power,
    this.yearCreation
)

fun DetailSuperheroe.transformToDetailEntity(): DetailSuperheroeEntity =
    DetailSuperheroeEntity(
        this.id,
        this.name,
        this.origin,
        this.imageLink,
        this.power,
        this.yearCreation,
        this.colour,
        this.translation
    )