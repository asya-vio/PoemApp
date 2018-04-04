package com.anastasia.poemapp.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Poem(
        @PrimaryKey
        val id : Int,
        var name : String?,
        var author: Author?,
        var type: Type?,
        var year : String?,
        val text : String,
        var status : Status,
        var photo : String?
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

        /*fun getNationalPoems() : ArrayList<Poem>{

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
        }*/

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
            result += text.subSequence(0, 70)
            result += "..."
        }

        return result
    }

}