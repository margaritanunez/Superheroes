package com.example.superheroes.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.superheroes.data.local.DetailSuperheroeEntity
import com.example.superheroes.data.local.SuperheroeDao
import com.example.superheroes.data.local.SuperheroeEntity
import com.example.superheroes.data.remote.DetailSuperheroe
import com.example.superheroes.data.remote.Superheroe
import com.example.superheroes.data.remote.SuperheroeApi

class Repository(private val superheroeApi : SuperheroeApi, private val superheroeDao: SuperheroeDao) {

    //Listado de todos los superheroes
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

    //Detalle de acuerdo al ID seleccionado

    fun getDetailSuperheroeEntity(id: Int): LiveData<DetailSuperheroeEntity> =
        superheroeDao.getDetailSelectedSuperheroe(id)

    suspend fun chargeDetailSuperheroe(id: Int){
        try {
            val responseDetail = superheroeApi.getDataDetailSuperheroe(id)
            if (responseDetail.isSuccessful) {
                val respDetail = responseDetail.body()
                respDetail?.let {
                    val detailsSuperheroeEntity = it.transformToDetailEntity()
                    superheroeDao.insertDetailSelectedSuperheroe(detailsSuperheroeEntity)
                }
            }else {
                Log.e("repository", responseDetail.errorBody().toString())
            }
        }catch (exception: Exception){
            Log.e("catch", "")
        }
    }

}

/* referencia para hacer la función transformToDetailEntity
   @PrimaryKey val id: Int,
    val name: String,
    val origin: String,
    val imageLink: String,
    val power: String,
    val yearCreation: Int,
    val colour: String,
    val translation: Boolean

 */
/* referencia para hacer la función transformToEntity
 val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("origen") val origin: String,
    @SerializedName("imagenLink") val imageLink: String,
    @SerializedName("poder") val power: String,
    @SerializedName("Año_creacion") val yearCreation: Int
 */