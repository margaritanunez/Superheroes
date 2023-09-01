package com.example.superheroes.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.superheroes.data.local.SuperheroeDao
import com.example.superheroes.data.local.SuperheroeEntity
import com.example.superheroes.data.remote.Superheroe
import com.example.superheroes.data.remote.SuperheroeApi

class Repository(private val superheroeApi : SuperheroeApi, private val superheroeDao: SuperheroeDao) {

    fun getSuperheroeEntity(): LiveData<List<SuperheroeEntity>> = superheroeDao.getAllSuperheroes()

    suspend fun chargeAllSuperheroes(){
        try {
            val response = superheroeApi.getDataSuperheroe()
            if (response.isSuccessful){
                val resp = response.body()
                resp?.let {
                    val superheroesEntity= it.map {it.transformToEntity()}
                    superheroeDao.insertAllSuperheroes(superheroesEntity)
                }
            } else {
                Log.e("repository", response.errorBody().toString())
            }
        } catch (exception: Exception) {
            Log.e("catch", "")
        }
    }

    private fun Superheroe.transformToEntity(): SuperheroeEntity = SuperheroeEntity(
        this.id,
        this.name,
        this.origin,
        this.imageLink,
        this.power,
        this.yearCreation
    )
}

/* referencia para hacer la función
 val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("origen") val origin: String,
    @SerializedName("imagenLink") val imageLink: String,
    @SerializedName("poder") val power: String,
    @SerializedName("Año_creacion") val yearCreation: Int
 */