package com.anastasia.poemapp.AppBase

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.anastasia.poemapp.Models.Poem

@Dao
interface PoemsDao {

    @Query("SELECT * FROM Poem")
    fun getAll(): List<Poem>

    @Insert
    fun insertAll(photos: List<Poem>)
}