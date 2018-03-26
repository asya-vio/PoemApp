package com.anastasia.poemapp.Activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.anastasia.poemapp.Adapters.PoemRecyclerViewAdapter
import com.anastasia.poemapp.MainActivity
import com.anastasia.poemapp.Models.Author
import com.anastasia.poemapp.Models.Poem
import com.anastasia.poemapp.R
import kotlinx.android.synthetic.main.activity_author.*
import kotlinx.android.synthetic.main.app_bar_author.*

class AuthorActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author)
        setSupportActionBar(toolbar_author)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout_author, toolbar_author, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_author.addDrawerListener(toggle)
        toggle.syncState()

        nav_view_author.setNavigationItemSelectedListener(this)

        val authorId : Int
        authorId = intent.getIntExtra("author_id", -1)

        val currentAuthor = Author.AuthorList.getAuthorById(authorId)

        if (currentAuthor != null){

            title = currentAuthor.firstName + " " + currentAuthor.lastName
            showPoems(currentAuthor)
        }
        else {
            Toast.makeText(this,"Произведения не найдены", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout_author.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_author.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.author, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
    fun showPoems(author: Author){
        val recyclerViewPoems = findViewById<RecyclerView>(R.id.author_recycler_view)
        recyclerViewPoems.layoutManager = GridLayoutManager(this, 2)
        val adapter = PoemRecyclerViewAdapter(Poem.PoemList.getPoemsByAuthor(author), applicationContext)
        recyclerViewPoems.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_all -> {
                val extras = Bundle()
                extras.putString("selectedItem", getString(R.string.all_authors))
                val intent = Intent(this,
                        MainActivity::class.java).putExtras(extras)
                startActivity(intent)
            }
            R.id.nav_national -> {
                val extras = Bundle()
                extras.putString("selectedItem", getString(R.string.national_authors))
                val intent = Intent(this,
                        MainActivity::class.java).putExtras(extras)
                startActivity(intent)
            }
            R.id.nav_foreign -> {
                val extras = Bundle()
                extras.putString("selectedItem", getString(R.string.foreign_authors))
                val intent = Intent(this,
                        MainActivity::class.java).putExtras(extras)
                startActivity(intent)
            }
        }
        drawer_layout_author.closeDrawer(GravityCompat.START)
        return true
    }
}
