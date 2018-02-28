package com.anastasia.poemapp.Adapters

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anastasia.poemapp.Models.Poem
import com.anastasia.poemapp.R
import com.anastasia.poemapp.Interfaces.RecyclerViewClickListener
import android.R.attr.onClick
import android.widget.Toast
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import com.anastasia.poemapp.Activities.PoemActivity


/**
 * Created by Anastasia on 20.02.2018.
 */
class CustomRecyclerViewAdapter (private val listData : ArrayList<Poem>, context: Context) :
            RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>(){

    var mContext = context
    private val INTENT_POEM_ID = "poem_id"


    private val mListener: RecyclerViewClickListener? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomRecyclerViewAdapter.ViewHolder {

            val view : View = LayoutInflater.from(parent.context).
                    inflate(R.layout.card_view, parent, false)

            //val cardView = view.findViewById<CardView>(R.id.card_view)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: CustomRecyclerViewAdapter.ViewHolder, position: Int) {
            holder.bindData(listData[position])

            holder.setOnRecyclerViewClickListener(object : RecyclerViewClickListener {
                override fun onRecyclerViewClickListener(view: View, position: Int) {
                    val intent = Intent(mContext, PoemActivity::class.java).putExtra("poem_id", listData[position].id)
                    mContext!!.startActivity(intent)
                    //Toast.makeText(mContext, "Открываю стихотворение", Toast.LENGTH_LONG).show()
                }
            })
        }

        override fun getItemCount() = listData.size

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            var recyclerViewClickListener: RecyclerViewClickListener? = null

            fun setOnRecyclerViewClickListener(itemClickListener: RecyclerViewClickListener) {

                this.recyclerViewClickListener = itemClickListener

            }

            override fun onClick(p0: View?) {
                this.recyclerViewClickListener!!.onRecyclerViewClickListener(p0!!, adapterPosition)
            }

            fun bindData(poem: Poem) {

                val poemNameTextView = itemView.findViewById<TextView>(R.id.poem_name)
                poemNameTextView.text = poem.name

                val authorNAmeTextView = itemView.findViewById<TextView>(R.id.author_name)
                authorNAmeTextView.text = poem.getAuthorName()

                //TODO должны подгружаться изображения с сервера через их адрес

                val poemImage = itemView.findViewById<ImageView>(R.id.photo)
                poemImage.setImageResource(R.drawable.ic_author)


                val beginTextView = itemView.findViewById<TextView>(R.id.begin_text)
                beginTextView.text = poem.getBeginText()

                itemView.setOnClickListener(this)

            }
        }

    }
