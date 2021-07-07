package com.example.databinding_practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding_practice.databinding.RecyclerItemBinding
import com.example.databinding_practice.model.RecyclerItemModel

class RecyclerAdapter(var recyclerItems: ArrayList<RecyclerItemModel>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.binding.recyclerItemTextView.text = recyclerItems[position].text
        recyclerItems[position].imageMipmapSource?.let { holder.binding.recyclerItemImageView.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return recyclerItems.size
    }
}