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
        //var status : Status
) {
     object PoemList : ArrayList<Poem>(){

         fun getPoemById(id: Int) : Poem?{
             PoemList.forEach{
                 if (it.id == id){
                     return it
                 }
             }
             return null
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