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
import com.anastasia.poemapp.Models.Author
import com.anastasia.poemapp.Models.Poem
import com.anastasia.poemapp.Models.Status
import com.anastasia.poemapp.Models.Type
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.OkHttpClient
import okhttp3.Request

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
        if(Poem.PoemList.isEmpty()){
            loadData()
        }
        showAuthors(getString(R.string.all_authors))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null){
            val bundle = intent.extras
            val selectedItem = bundle.getString("selectedItem")
            when(selectedItem){
                getString(R.string.all_authors) ->{
                    showAuthors(getString(R.string.all_authors))
                    //onNavigationItemSelected(nav_view.menu.getItem(R.id.nav_all))
                }
                getString(R.string.national_authors) ->{
                    showAuthors(getString(R.string.national_authors))
                    //onNavigationItemSelected(nav_view.menu.getItem(R.id.nav_national))
                }
                getString(R.string.foreign_authors) ->{
                    showAuthors(getString(R.string.foreign_authors))
                    //onNavigationItemSelected(nav_view.menu.getItem(R.id.nav_foreign))
                }
            }
        }
        else showAuthors(getString(R.string.all_authors))
    }
    fun loadData (){

        var author1 = Author(1, "Есенин", "Сергей", "Александрович", Type.NATIONAL, null)
        var author2 = Author(2, "Бернс", "Роберт", null, Type.FOREIGN, null)
        var author3 = Author(3, "Хайям", "Омар", null, Type.FOREIGN, null)
        var author4 = Author(4, "Асадов", "Эдуард", null, Type.NATIONAL, null)

        Author.AuthorList.add(author1)
        Author.AuthorList.add(author2)
        Author.AuthorList.add(author3)
        Author.AuthorList.add(author4)
        /*var text : String = "О Брут, где Кассий, где часовой,\n" +
                "Глашатай идеи священной,\n" +
                "Не раз отводивший душу с тобой\n" +
                "В вечерних прогулках над Сеной?\n" +
                "\n" +
                "На землю взирали вы свысока,\n" +
                "Паря наравне с облаками.\n" +
                "Была туманней, чем облака,\n" +
                "Идея, владевшая вами.\n" +
                "\n" +
                "О Брут, где Кассий, твой друг, твой брат,\n" +
                "О мщенье забывший так рано?\n" +
                "Ведь он на Неккаре стал, говорят,\n" +
                "Чтецом при особе тирана!»\n" +
                "\n" +
                "Но Брут отвечает: «Ты круглый дурак!\n" +
                "О, близорукость поэта!\n" +
                "Мой Кассий читает тирану, но так,\n" +
                "Чтоб сжить тирана со света.\n" +
                "\n" +
                "Стихи Мацерата выкопал плут,\n" +
                "Страшней кинжала их звуки.\n" +
                "Рано иль поздно тирану капут,\n" +
                "Бедняга погибнет от скуки"*/


        var text = "Серебристая дорога,\n" +
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

        var poem3 = Poem(3, "Серебристая дорога", author1, Type.NATIONAL, "1917", text, Status.OLD, null )

        Poem.PoemList.add(poem3)



        /*var poem1 = Poem(1, "Серебристая дорога", author, Type.NATIONAL, "1918", text, Status.OLD, null )
        var poem2 = Poem(2, "Каменная дорога", author, Type.NATIONAL, "1918", text, Status.OLD, null )

        var poem4 = Poem(4, "Какая-то дорога", author, Type.FOREIGN, "1918", text, Status.OLD, null )
        var poem5 = Poem(5, "Ужасная дорога", author, Type.FOREIGN, "1918", text, Status.OLD, null )

        Poem.PoemList.add(poem1)
        Poem.PoemList.add(poem2)
        Poem.PoemList.add(poem3)
        Poem.PoemList.add(poem4)
        Poem.PoemList.add(poem5)

        Thread(Runnable {

            val client = OkHttpClient()

            val request = Request.Builder().url("https://api.github.com/users/square/repos")
                    .build()

            val response = client.newCall(request).execute()
            val responseText = response.body()!!.string()

            val repos = Gson().fromJson(responseText,
                    Poem.PoemList::class.java)

        }).start()*/
    }

    fun showAuthors(param: String){
        val recyclerViewAuthors = findViewById<RecyclerView>(R.id.main_recycler_view)
        recyclerViewAuthors.layoutManager = GridLayoutManager(this, 2)
        when(param){
            getString(R.string.all_authors) ->{
                val adapter = AuthorRecyclerViewAdapter(Author.AuthorList, applicationContext)
                recyclerViewAuthors.adapter = adapter
                title = getString(R.string.all_authors)
            }
            getString(R.string.national_authors) ->{
                val adapter = AuthorRecyclerViewAdapter(Author.AuthorList.getNational(), applicationContext)
                recyclerViewAuthors.adapter = adapter
                title = getString(R.string.national_authors)
            }
            getString(R.string.foreign_authors) ->{
                val adapter = AuthorRecyclerViewAdapter(Author.AuthorList.getForeign(), applicationContext)
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
