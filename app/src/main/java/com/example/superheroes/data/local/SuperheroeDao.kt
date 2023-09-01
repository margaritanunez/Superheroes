package com.example.superheroes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuperheroeDao {

    // Para un listado de superheroes

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertAllSuperheroes(superheroeList: List<SuperheroeEntity>)

    @Query("SELECT * FROM superheroe_table ORDER BY id ASC")
    fun getAllSuperheroes(): LiveData<List<SuperheroeEntity>>
}