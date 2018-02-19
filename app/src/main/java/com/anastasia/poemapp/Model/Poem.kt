package com.anastasia.poemapp.Model

import android.media.Image

/**
 * Created by Anastasia on 19.02.2018.
 */
data class Poem(
        val id : Int,
        var name : String?,
        var author: Author?,
        var type: Type?,
        var year : String?,
        val text : String,
        var status : Status,
        var photo : Image?
) {
    companion object PoemList : ArrayList<Poem>()

    fun getNationalPoems() : ArrayList<Poem>{

        var result = ArrayList<Poem>()

        PoemList.forEach{
            if (it?.type == Type.NATIONAL){
                result.add(it)
            }
        }
        return result
    }

    fun getForeignPoems() : ArrayList<Poem>{
        var result = ArrayList<Poem>()

        PoemList.forEach {
            if (it?.type == Type.FOREIGN){
                result.add(it)
            }
        }
        return result
    }

    fun getPoemsByAuthor(inAuthor: Author) : ArrayList<Poem>{
        var result = ArrayList<Poem>()

        PoemList.forEach {
            if (it?.author == inAuthor){
                result.add(it)
            }
        }
        return result
    }

}