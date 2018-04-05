package com.anastasia.poemapp.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

data class Poem(
        val id : Int,
        var name : String?,
        var authorId : Int,
        var author: Author?,
        var year : String?,
        val text : String
) {
     class List : ArrayList<Poem>()

    fun getAuthorName() : String{
        var result = ""
        if (author != null){
            result += author?.lastName + " "
            result += author?.firstName
        }
        return result
    }

    fun getBeginText() : String{
        var result = ""
        if (text != null){
            if(text.length > 70){
                result += text.subSequence(0, 70)
                result += "..."
            }
            else{
                result += text
            }

        }

        return result
    }


}