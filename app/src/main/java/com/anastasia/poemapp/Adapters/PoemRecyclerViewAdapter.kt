package com.anastasia.poemapp.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import com.anastasia.poemapp.Models.Poem
import com.anastasia.poemapp.R
import com.anastasia.poemapp.Interfaces.RecyclerViewClickListener
import android.content.Intent
import com.anastasia.poemapp.Activities.PoemActivity

class PoemRecyclerViewAdapter(private val poemList : ArrayList<Poem>,context: Context) :
            RecyclerView.Adapter<PoemRecyclerViewAdapter.ViewHolder>(){

    var mContext = context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val view : View = LayoutInflater.from(parent.context).
                    inflate(R.layout.poem_card_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: PoemRecyclerViewAdapter.ViewHolder, position: Int) {
            holder.bindData(poemList[position])

            holder.setOnRecyclerViewClickListener(object : RecyclerViewClickListener {
                override fun onRecyclerViewClickListener(view: View, position: Int) {
                    val intent = Intent(mContext, PoemActivity::class.java).putExtra("poem_id", poemList[position].id)
                    mContext!!.startActivity(intent)
                }
            })
        }

        override fun getItemCount() = poemList.size

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

                val poemYearTV = itemView.findViewById<TextView>(R.id.poem_year)
                poemYearTV.text = poem.year

                val beginTextView = itemView.findViewById<TextView>(R.id.begin_text)
                beginTextView.text = poem.getBeginText()

                itemView.setOnClickListener(this)

            }
        }

    }
