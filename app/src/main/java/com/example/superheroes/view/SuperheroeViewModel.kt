package com.example.superheroes.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroes.data.Repository
import com.example.superheroes.data.local.SuperheroeDataBase
import com.example.superheroes.data.remote.SuperheroeRetrofitClient
import kotlinx.coroutines.launch

class SuperheroeViewModel (application:Application): AndroidViewModel(application){

    private lateinit var repository: Repository

    init {
        val superheroeApi = SuperheroeRetrofitClient.getRetrofitSuperheroe()
        val superheroeDataBase = SuperheroeDataBase.getDatabase(application).getSuperheroeDao()
        repository = Repository(superheroeApi, superheroeDataBase)
    }

    //Listado superheroes

    fun superheroeLiveData() = repository.getSuperheroeEntity()
    fun getAlllSuperheroesViewModel() = viewModelScope.launch {
        repository.chargeAllSuperheroes()
    }

    //Detalle superheroes
    fun detailSuperheroeLiveData(id: Int) = repository.getDetailSuperheroeEntity(id)
    fun getDetailsSuperheroeViewModel(id: Int) = viewModelScope.launch {
        repository.chargeDetailSuperheroe(id)
    }
}