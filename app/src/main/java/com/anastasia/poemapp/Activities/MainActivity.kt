package com.anastasia.poemapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.anastasia.poemapp.Adapters.AuthorRecyclerViewAdapter
import com.anastasia.poemapp.Adapters.PoemRecyclerViewAdapter
import com.anastasia.poemapp.AppBase.App
import com.anastasia.poemapp.Models.Author
import com.anastasia.poemapp.Models.Poem
import com.anastasia.poemapp.Models.Status
import com.anastasia.poemapp.Models.Type
import com.google.gson.Gson
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.*
import java.io.IOException
import android.R.attr.data
import com.anastasia.poemapp.AppBase.loadAuthors
import com.anastasia.poemapp.AppBase.loadPoems
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import java.util.Arrays.asList


open class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout_main, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_main.addDrawerListener(toggle)
        toggle.syncState()

        nav_view_main.setNavigationItemSelectedListener(this)
//        if (Author.AuthorList.isEmpty()) {
//            loadData()
//        }
        loadData()

        showAuthors(getString(R.string.all_authors))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            val bundle = intent.extras
            val selectedItem = bundle.getString("selectedItem")
            when (selectedItem) {
                getString(R.string.all_authors) -> {
                    showAuthors(getString(R.string.all_authors))
                    //onNavigationItemSelected(nav_view.menu.getItem(R.id.nav_all))
                }
                getString(R.string.national_authors) -> {
                    showAuthors(getString(R.string.national_authors))
                    //onNavigationItemSelected(nav_view.menu.getItem(R.id.nav_national))
                }
                getString(R.string.foreign_authors) -> {
                    showAuthors(getString(R.string.foreign_authors))
                    //onNavigationItemSelected(nav_view.menu.getItem(R.id.nav_foreign))
                }
            }
        } else showAuthors(getString(R.string.all_authors))
    }

    private fun loadData() {

        //потом убрать
        Author.AuthorList.clear()

        if ((application as App).isOnline()) {
//            val client = OkHttpClient()
//            val request = Request.Builder()
//                    .url("https://api.myjson.com/bins/v811f")
//                    .build()
//            client.newCall(request).enqueue(object : Callback {
//
//                override fun onResponse(call: Call?, response: Response?) {
//
//                    val responseText = response?.body()!!.string()
//                    val authors = Gson().fromJson(responseText, Author.AuthorList::class.java)
//
//                    authors.forEach {
//                        if (it.typeId == 1) {
//                            it.type = Type.NATIONAL
//                        } else if (it.typeId == 2) {
//                            it.type = Type.FOREIGN
//                        }
//                    }
//                    Paper.book().write("authors", authors)
//
//                    runOnUiThread {
//                        showAuthors(getString(R.string.all_authors))
//                    }
//                }
//
//            })
            loadAuthors()
            loadPoems()
            runOnUiThread {
                showAuthors(getString(R.string.all_authors))
            }

        } else {
            showAuthors(getString(R.string.all_authors))
        }

    }

    fun showAuthors(param: String) {
        val recyclerViewAuthors = findViewById<RecyclerView>(R.id.main_recycler_view)
        recyclerViewAuthors.layoutManager = GridLayoutManager(this, 2)
        when (param) {
            getString(R.string.all_authors) -> {
                //val adapter = AuthorRecyclerViewAdapter(Author.AuthorList, applicationContext)
                val authors: Author.AuthorList = Paper.book().read("authors")
                val adapter = AuthorRecyclerViewAdapter(authors, applicationContext)
                recyclerViewAuthors.adapter = adapter
                title = getString(R.string.all_authors)
            }
            getString(R.string.national_authors) -> {
                //val adapter = AuthorRecyclerViewAdapter(Author.AuthorList.getNational(), applicationContext)
                val authors: Author.AuthorList = Paper.book().read("authors")
                val adapter = AuthorRecyclerViewAdapter(authors.getNational(), applicationContext)
                recyclerViewAuthors.adapter = adapter
                title = getString(R.string.national_authors)
            }
            getString(R.string.foreign_authors) -> {
                //val adapter = AuthorRecyclerViewAdapter(Author.AuthorList.getForeign(), applicationContext)
                val authors: Author.AuthorList = Paper.book().read("authors")
                val adapter = AuthorRecyclerViewAdapter(authors.getForeign(), applicationContext)
                recyclerViewAuthors.adapter = adapter
                title = getString(R.string.foreign_authors)
            }
        }
    }


    override fun onBackPressed() {
        if (drawer_layout_main.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_main.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_all -> {
                showAuthors(getString(R.string.all_authors))
                title = getString(R.string.all_authors)
            }
            R.id.nav_national -> {
                showAuthors(getString(R.string.national_authors))
                title = getString(R.string.national_authors)
            }
            R.id.nav_foreign -> {
                showAuthors(getString(R.string.foreign_authors))
                title = getString(R.string.foreign_authors)
            }
        }
        drawer_layout_main.closeDrawer(GravityCompat.START)
        return true
    }
}
