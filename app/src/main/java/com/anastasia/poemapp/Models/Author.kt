package com.anastasia.poemapp.Models

import android.graphics.Bitmap

/**
 * Created by Anastasia on 19.02.2018.
 */
class Author (
        val id : Int,
        var lastName : String,
        var firstName : String?,
        var secondName : String?,
        var type : Type,
        var photo : String?
){
    object AuthorList : ArrayList<Author>(){

        fun getAuthorById(id: Int) : Author?{
            AuthorList.forEach{
                if (it.id == id){
                    return it
                }
            }
            return null
        }

        fun loadAuthorsFromDb(){}

        fun getForeign() : ArrayList<Author>{
            var result = ArrayList<Author>()

            AuthorList.forEach {
                if (it?.type == Type.FOREIGN){
                    result.add(it)
                }
            }
            return result
        }

        fun getNational() : ArrayList<Author>{
            var result = ArrayList<Author>()

            AuthorList.forEach {
                if (it?.type == Type.NATIONAL){
                    result.add(it)
                }
            }
            return result
        }
    }


}