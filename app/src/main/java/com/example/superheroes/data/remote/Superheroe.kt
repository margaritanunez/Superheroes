package com.example.superheroes.data.remote

import com.google.gson.annotations.SerializedName

data class Superheroe(
    val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("origen") val origin: String,
    @SerializedName("imagenLink") val imageLink: String,
    @SerializedName("poder") val power: String,
    @SerializedName("Año_creacion") val yearCreation: Int
)


/* Modelar datos del POJO a partir de los proporcionados por la API
{
        "id": 1,
        "nombre": "BATMAN",
        "origen": "Gotham City",
        "imagenLink": "https://cloudfront-us-east-1.images.arcpublishing.com/metroworldnews/OMMXHLDAABDBVHRUH2FPDLVZCY.jpg",
        "poder": "No tiene super poderes, recurre a su intelecto",
        "Año_creacion": 1939
    },

 */