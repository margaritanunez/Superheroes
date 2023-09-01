package com.example.superheroes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [SuperheroeEntity::class], version = 1)
abstract class SuperheroeDataBase : RoomDatabase(){

    abstract fun getSuperheroeDao() : SuperheroeDao

    companion object{
        @Volatile
        private var INSTANCE: SuperheroeDataBase? = null

        fun getDatabase(context: Context): SuperheroeDataBase{
            val tempInstance = INSTANCE
            if (tempInstance !=null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuperheroeDataBase::class.java,
                    "superheroes_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}