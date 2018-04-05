package com.anastasia.poemapp.AppBase

import com.anastasia.poemapp.Models.Author
import com.anastasia.poemapp.Models.Poem
import com.anastasia.poemapp.Models.Type
import com.google.gson.Gson
import io.paperdb.Paper
import okhttp3.*
import java.io.IOException

fun loadAuthorsFromServer() {
    val client = OkHttpClient()
    val request = Request.Builder()
            .url("https://api.myjson.com/bins/v811f")
            .build()
    client.newCall(request).enqueue(object : Callback {

        override fun onResponse(call: Call?, response: Response?) {

            val responseText = response?.body()!!.string()
            val authors = Gson().fromJson(responseText, Author.List::class.java)

            authors.forEach {
                if (it.typeId == 1) {
                    it.type = Type.NATIONAL
                } else if (it.typeId == 2) {
                    it.type = Type.FOREIGN
                }
            }
            Paper.book().write("authors", authors)
        }

        override fun onFailure(call: Call?, e: IOException?) {
            println("Failed to execute request")
        }
    })
}

fun loadPoemsFromServer() {
    val client = OkHttpClient()
    val request = Request.Builder()
            .url("https://api.myjson.com/bins/eqr0j")
            .build()
    client.newCall(request).enqueue(object : Callback {

        override fun onResponse(call: Call?, response: Response?) {

            val responseText = response?.body()!!.string()
            val poems = Gson().fromJson(responseText, Poem.List::class.java)

            poems.forEach {
                if(it.authorId != null){
                    val authors: Author.List = Paper.book().read("authors")
                    it.author = getAuthorById(it.authorId)
                }
            }
            Paper.book().write("poems", poems)
        }

        override fun onFailure(call: Call?, e: IOException?) {
            println("Failed to execute request")
        }
    })
}

fun isDBEmpty(): Boolean{
    try{
        val authors: Author.List = Paper.book().read("authors")
        return authors.isEmpty()
    }
    catch (e: Exception){
        return true
    }


}
fun getAuthorsFromDB(): Author.List{

    try{
        return Paper.book().read("authors")
    }
    catch (e: Exception){
        return getDefaultAuthor()
    }
}

fun getNationalAuthorsFromDB(): Author.List{

    try{
        val authors: Author.List = Paper.book().read("authors")
        val result : Author.List = Author.List()
        for(author in authors){
            if(author.type == Type.NATIONAL)
                result.add(author)
        }
        return result
    }
    catch (e: Exception){
        return getDefaultAuthor()
    }
}

fun getForeignAuthorsFromDB(): Author.List{

    try{
        val authors: Author.List = Paper.book().read("authors")
        val result : Author.List = Author.List()
        for(author in authors){
            if(author.type == Type.FOREIGN)
                result.add(author)
        }
        return result
    }
    catch (e: Exception){
        return getDefaultAuthor()
    }
}

fun getAuthorById(id:Int): Author{
    try{
        val authors: Author.List = Paper.book().read("authors")
        for(author in authors){
            if(author.id == id)
                return author
        }
        return getDefaultAuthor()[0]
    }
    catch (e: Exception){
        return getDefaultAuthor()[0]
    }
}

fun getDefaultAuthor():Author.List{

    val defaultAuthor : Author.List = Author.List()

    val defAuthor = Author(
            id = 1,
            firstName = "null",
            lastName = "null",
            photo = "",
            type = Type.FOREIGN,
            typeId = 1
    )
    defaultAuthor.add(defAuthor)

    return defaultAuthor
}

fun getPoemsFromDB(): Poem.List {
    try{
        return Paper.book().read("poems")
    }
    catch(e: Exception){
        return getDefaultPoem()
    }
}

fun getDefaultPoem(): Poem.List{
    val defaultPoem : Poem.List = Poem.List()

    val defPoem = Poem(
            id = 1,
            author = null,
            authorId = 1,
            name = "",
            text = "",
            year = ""

    )
    defaultPoem.add(defPoem)

    return defaultPoem
}


fun getPoemById(id:Int): Poem{
    try{
        val poems: Poem.List = Paper.book().read("poems")
        for(poem in poems){
            if(poem.id == id)
                return poem
        }
        return getDefaultPoem()[0]
    }
    catch (e: Exception){
        return getDefaultPoem()[0]
    }
}


fun getPoemsByAuthor(author: Author): Poem.List{
    try{
        val poems: Poem.List = Paper.book().read("poems")
        val result: Poem.List = Poem.List()
        for(poem in poems){
            if(poem.author == author)
                result.add(poem)
        }
        return result
    }
    catch (e: Exception){
        return getDefaultPoem()
    }
}