package com.anastasia.poemapp

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.anastasia.poemapp.Adapters.CustomRecyclerViewAdapter
import com.anastasia.poemapp.Models.Author
import com.anastasia.poemapp.Models.Poem
import com.anastasia.poemapp.Models.Status
import com.anastasia.poemapp.Models.Type
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        title = "Стихотворцы"

        val recyclerViewAllPoem = findViewById<RecyclerView>(R.id.main_recycler_view)
        //recyclerViewAllPoem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewAllPoem.layoutManager = LinearLayoutManager(this)

        var author = Author(2, "Есенин", "Сергей", "Александрович", null)

        val text : String = "Серебристая дорога,\n" +
                "Ты зовешь меня куда?\n" +
                "Свечкой чисточетверговой\n" +
                "Над тобой горит звезда.\n" +
                "\n" +
                "Грусть ты или радость теплишь?\n" +
                "Иль к безумью правишь бег?\n" +
                "Помоги мне сердцем вешним\n" +
                "Долюбить твой жесткий снег.\n" +
                "\n" +
                "Дай ты мне зарю на дровни,\n" +
                "Ветку вербы на узду.\n" +
                "Может быть, к вратам господним\n" +
                "Сам себя я приведу."

        var poem = Poem(2, "Серебристая дорога", author, Type.NATIONAL, "1917", text, Status.OLD, null )


        var listPoem = ArrayList<Poem>()
        listPoem.add(poem)
        listPoem.add(poem)
        listPoem.add(poem)

        val adapter = CustomRecyclerViewAdapter(listPoem)
        recyclerViewAllPoem.adapter = adapter


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_all -> {

            }
            R.id.nav_national -> {

            }
            R.id.nav_foreign -> {

            }
            R.id.nav_load_new -> {

            }

            R.id.nav_manage -> {

            }
            /*
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }*/
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
