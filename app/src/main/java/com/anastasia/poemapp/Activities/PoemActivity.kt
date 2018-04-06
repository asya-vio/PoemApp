package com.anastasia.poemapp.Activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.anastasia.poemapp.AppBase.getPoemById
import com.anastasia.poemapp.MainActivity
import com.anastasia.poemapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_poem.*
import kotlinx.android.synthetic.main.app_bar_poem.*
import kotlinx.android.synthetic.main.app_bar_poem.view.*
import kotlinx.android.synthetic.main.content_poem.*

class PoemActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poem)
        setSupportActionBar(toolbar_poem)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout_poem, toolbar_poem, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_poem.addDrawerListener(toggle)
        toggle.syncState()

        nav_view_poem.setNavigationItemSelectedListener(this)

        val poemId: Int
        poemId = intent.getIntExtra("poem_id", -1)

        val currentPoem = getPoemById(poemId)

        if (currentPoem != null) {

            try {
                Picasso.get().load(currentPoem.author!!.photo).into(poem_photo)
            } catch (e: Exception) {
                poem_photo.setImageResource(R.drawable.author)
            }
            author_name.text = currentPoem.getAuthorName()
            year.text = currentPoem.year
            poem_text.text = currentPoem.text

            title = currentPoem.name

        } else {
            Toast.makeText(this, "Произведение не найдено", Toast.LENGTH_LONG).show()
        }
    }


    override fun onBackPressed() {
        if (drawer_layout_poem.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_poem.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.poem, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    //TODO узнать, можно ли избавиться от этого метода здесь. Как подгружать разные состояния main activity. Наследоввание не получилось
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_all -> {
                val extras = Bundle()
                extras.putString("selectedItem", getString(R.string.all_poems))
                val intent = Intent(this,
                        MainActivity::class.java).putExtras(extras)
                startActivity(intent)
            }
            R.id.nav_national -> {
                val extras = Bundle()
                extras.putString("selectedItem", getString(R.string.national_poems))
                val intent = Intent(this,
                        MainActivity::class.java).putExtras(extras)
                startActivity(intent)
            }
            R.id.nav_foreign -> {
                val intent = Intent(this,
                        MainActivity::class.java).putExtra("selectedItem", getString(R.string.foreign_poems))
                startActivity(intent)

            }
        }

        drawer_layout_poem.closeDrawer(GravityCompat.START)
        return true
    }
}
