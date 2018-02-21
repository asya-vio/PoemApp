package com.anastasia.poemapp.Adapters

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anastasia.poemapp.Models.Poem
import com.anastasia.poemapp.R
import com.anastasia.poemapp.Interfaces.RecyclerViewClickListener
import android.R.attr.onClick
import android.widget.Toast


/**
 * Created by Anastasia on 20.02.2018.
 */
class CustomRecyclerViewAdapter (private val listData : ArrayList<Poem>) :
            RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>(){

    private val mListener: RecyclerViewClickListener? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomRecyclerViewAdapter.ViewHolder {

            val view : View = LayoutInflater.from(parent.context).
                    inflate(R.layout.card_view, parent, false)

            val cardView = view.findViewById<CardView>(R.id.card_view)
            cardView.radius = 5.0F

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: CustomRecyclerViewAdapter.ViewHolder, position: Int) {
            holder.bindData(listData[position])
            //
        }

        override fun getItemCount() = listData.size

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            var recyclerViewClickListener: RecyclerViewClickListener? = null

            fun setOnRecyclerViewClickListener(itemClickListener: RecyclerViewClickListener) {

                this.recyclerViewClickListener = itemClickListener

            }

            override fun onClick(p0: View?) {
                this.recyclerViewClickListener!!.onRecyclerViewItemClick(p0!!, adapterPosition)
            }

            fun bindData(poem: Poem) {

                val poemNameTextView = itemView.findViewById<TextView>(R.id.poem_name)
                poemNameTextView.text = poem.name

                val authorNAmeTextView = itemView.findViewById<TextView>(R.id.author_name)
                authorNAmeTextView.text = poem.getAuthorName()

                val poemImage = itemView.findViewById<ImageView>(R.id.photo)
                poemImage.setImageResource(R.drawable.ic_author)
                //poemImage.setImageBitmap(poem?.photo)

                val beginTextView = itemView.findViewById<TextView>(R.id.begin_text)
                beginTextView.text = poem.getBeginText()

                itemView.setOnClickListener(this)

            }
        }

    }
