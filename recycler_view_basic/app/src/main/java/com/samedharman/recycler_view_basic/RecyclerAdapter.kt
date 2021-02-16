package com.samedharman.recycler_view_basic

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerAdapter(val animeList:ArrayList<AnimeModel>):RecyclerView.Adapter<RecyclerAdapter.AnimeViewHolder>() {
    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val iview = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return AnimeViewHolder(iview)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.itemView.recycler_textView.text = animeList.get(position).animeName

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,AnimeDetailActivity::class.java)
            intent.putExtra("animeName",animeList.get(position).animeName)
            intent.putExtra("animeImage",animeList.get(position).animeImage)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}