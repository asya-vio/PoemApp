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
        var photo : Bitmap?){

    //Как правильно объявлять поля класса?
    //Должны ли они быть приватными?
    //Как лучше хранить изображения?

    /*val lastName : String
    init{
        this.lastName = ""
    }
    private val firstName : String  = ""

    private val secondName : String = ""

    val photo :*/

}