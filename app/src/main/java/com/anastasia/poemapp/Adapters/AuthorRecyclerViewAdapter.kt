package com.anastasia.poemapp.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anastasia.poemapp.Activities.AuthorActivity
import com.anastasia.poemapp.Interfaces.RecyclerViewClickListener
import com.anastasia.poemapp.Models.Author
import com.anastasia.poemapp.R
import com.squareup.picasso.Picasso

class AuthorRecyclerViewAdapter(private val authorList : ArrayList<Author>, context: Context) :
            RecyclerView.Adapter<AuthorRecyclerViewAdapter.ViewHolder>(){

    var mContext = context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val view : View = LayoutInflater.from(parent.context).
                    inflate(R.layout.author_card_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: AuthorRecyclerViewAdapter.ViewHolder, position: Int) {
            holder.bindData(authorList[position])

            holder.setOnRecyclerViewClickListener(object : RecyclerViewClickListener {
                override fun onRecyclerViewClickListener(view: View, position: Int) {
                    val intent = Intent(mContext, AuthorActivity::class.java).putExtra("author_id", authorList[position].id)
                    mContext!!.startActivity(intent)
                }
            })
        }

        override fun getItemCount() = authorList.size

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            var recyclerViewClickListener: RecyclerViewClickListener? = null

            fun setOnRecyclerViewClickListener(itemClickListener: RecyclerViewClickListener) {

                this.recyclerViewClickListener = itemClickListener

            }

            override fun onClick(p0: View?) {
                this.recyclerViewClickListener!!.onRecyclerViewClickListener(p0!!, adapterPosition)
            }

            fun bindData(author: Author) {

                val authorNameTV = itemView.findViewById<TextView>(R.id.auth_name)
                authorNameTV.text = author.firstName + " " + author.lastName

                val authorIV = itemView.findViewById<ImageView>(R.id.auth_photo)

                try {
                    Picasso.get().load(author.photo).into(authorIV)
                }
                catch (e: Exception) {
                    authorIV.setImageResource(R.drawable.author)
                }

                itemView.setOnClickListener(this)

            }
        }

    }