package com.anastasia.poemapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.transition.Slide
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.anastasia.poemapp.Adapters.CustomRecyclerViewAdapter
import com.anastasia.poemapp.Models.Author
import com.anastasia.poemapp.Models.Poem
import com.anastasia.poemapp.Models.Status
import com.anastasia.poemapp.Models.Type
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

open class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        loadData()

        showPoems(getString(R.string.all_poems))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null){
            val bundle = intent.extras
            val selectedItem = bundle.getString("selectedItem")
            when(selectedItem){
                getString(R.string.all_poems) ->{
                    showPoems(getString(R.string.all_poems))
                    //onNavigationItemSelected(nav_view.menu.getItem(R.id.nav_all))
                }
                getString(R.string.national_poems) ->{
                    showPoems(getString(R.string.national_poems))
                    //onNavigationItemSelected(nav_view.menu.getItem(R.id.nav_national))
                }
                getString(R.string.foreign_poems) ->{
                    showPoems(getString(R.string.foreign_poems))
                    //onNavigationItemSelected(nav_view.menu.getItem(R.id.nav_foreign))
                }
            }
        }
        else showPoems(getString(R.string.all_poems))
    }
    fun loadData (){
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

        var poem1 = Poem(1, "Серебристая дорога", author, Type.NATIONAL, "1917", text, Status.OLD, null )
        var poem2 = Poem(2, "Каменная дорога", author, Type.NATIONAL, "1917", text, Status.OLD, null )
        var poem3 = Poem(3, "Не серебристая дорога", author, Type.NATIONAL, "1917", text, Status.OLD, null )
        var poem4 = Poem(4, "Какая-то дорога", author, Type.FOREIGN, "1917", text, Status.OLD, null )
        var poem5 = Poem(5, "Ужасная дорога", author, Type.FOREIGN, "1917", text, Status.OLD, null )

        Poem.PoemList.add(poem1)
        Poem.PoemList.add(poem2)
        Poem.PoemList.add(poem3)
        Poem.PoemList.add(poem4)
        Poem.PoemList.add(poem5)
    }

    fun showPoems(param: String){
        val recyclerViewPoems = findViewById<RecyclerView>(R.id.main_recycler_view)
        recyclerViewPoems.layoutManager = GridLayoutManager(this, 2)
        when(param){
            getString(R.string.all_poems) ->{
                val adapter = CustomRecyclerViewAdapter(Poem.PoemList, applicationContext)
                recyclerViewPoems.adapter = adapter
                title = getString(R.string.all_poems)
            }
            getString(R.string.national_poems) ->{
                val adapter = CustomRecyclerViewAdapter(Poem.PoemList.getNationalPoems(), applicationContext)
                recyclerViewPoems.adapter = adapter
                title = getString(R.string.national_poems)
            }
            getString(R.string.foreign_poems) ->{
                val adapter = CustomRecyclerViewAdapter(Poem.PoemList.getForeignPoems(), applicationContext)
                recyclerViewPoems.adapter = adapter
                title = getString(R.string.foreign_poems)
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
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
                showPoems(getString(R.string.all_poems))
                title = getString(R.string.all_poems)
            }
            R.id.nav_national -> {
                showPoems(getString(R.string.national_poems))
                title = getString(R.string.national_poems)
            }
            R.id.nav_foreign -> {
                showPoems(getString(R.string.foreign_poems))
                title = getString(R.string.foreign_poems)
            }
            R.id.nav_load_new -> {

            }

            R.id.nav_manage -> {

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
