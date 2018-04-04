package com.anastasia.poemapp.AppBase

import com.anastasia.poemapp.Models.Author
import com.anastasia.poemapp.Models.Poem
import com.anastasia.poemapp.Models.Type
import com.anastasia.poemapp.R
import com.google.gson.Gson
import io.paperdb.Paper
import okhttp3.*
import okhttp3.Request.*
import java.io.IOException


fun loadAuthors() {
    val client = OkHttpClient()
    val request = Request.Builder()
            .url("https://api.myjson.com/bins/v811f")
            .build()
    client.newCall(request).enqueue(object : Callback {

        override fun onResponse(call: Call?, response: Response?) {

            val responseText = response?.body()!!.string()
            val authors = Gson().fromJson(responseText, Author.AuthorList::class.java)

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



fun loadPoems() {
    val client = OkHttpClient()
    val request = Request.Builder()
            .url("https://api.myjson.com/bins/eqr0j")
            .build()
    client.newCall(request).enqueue(object : Callback {

        override fun onResponse(call: Call?, response: Response?) {

            val responseText = response?.body()!!.string()
            val poems = Gson().fromJson(responseText, Poem.PoemList::class.java)

            poems.forEach {
                if(it.authorId != null){
                    val authors: Author.AuthorList = Paper.book().read("authors")
                    it.author = authors.getAuthorById(it.authorId)
                }
            }
            Paper.book().write("poems", poems)
        }

        override fun onFailure(call: Call?, e: IOException?) {
            println("Failed to execute request")
        }
    })
}