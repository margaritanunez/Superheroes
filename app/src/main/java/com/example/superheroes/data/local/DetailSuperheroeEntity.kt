package com.example.superheroes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_superheroe_table")
data class DetailSuperheroeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val origin: String,
    val imageLink: String,
    val power: String,
    val yearCreation: Int,
    val colour: String,
    val translation: Boolean
)

/*Para modelar las variables de entidad
 val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("origen") val origin: String,
    @SerializedName("imagenLink") val imageLink: String,
    @SerializedName("poder") val power: String,
    @SerializedName("año_creacion") val yearCreation: Int,
    @SerializedName("color") val colour: String,
    @SerializedName("traduccion") val translation: Boolean
 */
