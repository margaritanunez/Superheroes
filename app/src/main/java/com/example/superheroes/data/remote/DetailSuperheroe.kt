package com.example.superheroes.data.remote

import com.google.gson.annotations.SerializedName

data class DetailSuperheroe(
    val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("origen") val origin: String,
    @SerializedName("imagenLink") val imageLink: String,
    @SerializedName("poder") val power: String,
    @SerializedName("año_creacion") val yearCreation: Int,
    @SerializedName("color") val colour: String,
    @SerializedName("traduccion") val translation: Boolean
)

/*
Modelado de data class
    "id": 1,
    "nombre": "BATMAN",
    "origen": "Gotham City",
    "imagenLink": "https://cloudfront-us-east-1.images.arcpublishing.com/metroworldnews/OMMXHLDAABDBVHRUH2FPDLVZCY.jpg",
    "poder": "No tiene super poderes, recurre a su intelecto",
    "año_creacion": 1939,
    "color": "negro",
    "traduccion": "true"
}
 */