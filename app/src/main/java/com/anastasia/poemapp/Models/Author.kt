package com.anastasia.poemapp.Models

data class Author (
        val id : Int,
        var lastName : String,
        var firstName : String?,
        //var secondName : String?,
        var type : Type,
        var typeId: Int,
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