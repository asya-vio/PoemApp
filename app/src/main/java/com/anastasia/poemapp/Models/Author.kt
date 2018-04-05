package com.anastasia.poemapp.Models

data class Author (
        val id : Int,
        var lastName : String,
        var firstName : String?,
        var type : Type,
        var typeId: Int,
        var photo : String?
){
    class List : ArrayList<Author>()
}