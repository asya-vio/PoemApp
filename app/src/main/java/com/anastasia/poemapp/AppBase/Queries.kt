package com.anastasia.poemapp.AppBase

import com.anastasia.poemapp.Models.Author
import com.anastasia.poemapp.Models.Type
import com.google.gson.Gson
import io.paperdb.Paper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Request.*

//fun loadAuthorsByType(id: Type): Author.AuthorList {
//    val httpClient = OkHttpClient()
//
//    // Создать запрос
//    val request = Builder()
//            .url("http://api.jsonbin.io/b/5ac26b892be5ef0bbf468526")
//            .build()
//
//    val response = httpClient.newCall(request).execute()
//
//    // Обработать полученные данные
//    val responseText = response.body()!!.string()
//
//    System.out.println(responseText)
//
//    val authors = Gson().fromJson(responseText, Author.AuthorList::class.java)
//
//    Paper.book().write("authors", authors)
//
//    val requestById: Author.AuthorList = Author.AuthorList
//
//    for (item in authors) {
//        if (item.type == id) {
//            requestById.add(item)
//        }
//    }
//
//    Paper.book("for-foods-"+id).write("foods",requestById)
//
//    return requestById
//}

fun loadAuthors(): Author.AuthorList {
    val httpClient = OkHttpClient()

    // Создать запрос
    val request = Builder()
            .url("http://api.jsonbin.io/b/5ac26b892be5ef0bbf468526")
            .build()

    val response = httpClient.newCall(request).execute()

    // Обработать полученные данные
    val responseText = response.body()!!.string()

    System.out.println(responseText)

    val authors = Gson().fromJson(responseText, Author.AuthorList::class.java)

    return authors
}
