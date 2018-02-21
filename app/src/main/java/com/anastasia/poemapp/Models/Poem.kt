package com.anastasia.poemapp.Models

import android.graphics.Bitmap

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
        var photo : Bitmap?
) {
    companion object PoemList : ArrayList<Poem>()

    fun getAuthorName() : String{
        var result = ""
        if (author != null){
            result += author?.lastName + " "
            result += author?.firstName!![0] + "." + author?.secondName!![0] + "."
        }
        return result
    }

    fun getBeginText() : String{
        var result = ""
        if (text != null){
            result += text.subSequence(0, 20)
            result += "..."
        }

        return result
    }

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