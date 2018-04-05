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
import com.anastasia.poemapp.AppBase.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


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

        if (isDBEmpty()) {
            loadData()
        }
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
                }
                getString(R.string.national_authors) -> {
                    showAuthors(getString(R.string.national_authors))
                }
                getString(R.string.foreign_authors) -> {
                    showAuthors(getString(R.string.foreign_authors))
                }
            }
        } else showAuthors(getString(R.string.all_authors))
    }

    private fun loadData() {

        if ((application as App).isOnline()) {
            loadAuthorsFromServer()
            loadPoemsFromServer()

        } else {
            showAuthors(getString(R.string.all_authors))
        }

    }

    fun showAuthors(param: String) {
        val recyclerViewAuthors = findViewById<RecyclerView>(R.id.main_recycler_view)
        recyclerViewAuthors.layoutManager = GridLayoutManager(this, 2)
        when (param) {
            getString(R.string.all_authors) -> {
                val adapter = AuthorRecyclerViewAdapter(getAuthorsFromDB(), applicationContext)
                recyclerViewAuthors.adapter = adapter
                title = getString(R.string.all_authors)
            }
            getString(R.string.national_authors) -> {
                val adapter = AuthorRecyclerViewAdapter(getNationalAuthorsFromDB(), applicationContext)
                recyclerViewAuthors.adapter = adapter
                title = getString(R.string.national_authors)
            }
            getString(R.string.foreign_authors) -> {
                val adapter = AuthorRecyclerViewAdapter(getForeignAuthorsFromDB(), applicationContext)
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
