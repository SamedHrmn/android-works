package com.example.yemek_tarifleri

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemek_tarifleri.databinding.RecyclerItemBinding

class ListeFragmentRecyclerAdapter(val yemekIsimList: ArrayList<String>, val yemekIdList: ArrayList<Int>) :
    RecyclerView.Adapter<ListeFragmentRecyclerAdapter.YemekListeHolder>() {

    class YemekListeHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekListeHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        return YemekListeHolder(binding)
    }

    override fun onBindViewHolder(holder: YemekListeHolder, position: Int) {
        holder.binding.recyclerIsimText.text = yemekIsimList[position]
        holder.binding.recyclerIsimText.setOnClickListener {
            val action =
                YemekListeFragmentDirections.actionYemekListeFragmentToYemekDetay(navigateInfo = "fromRecycler", itemId = yemekIdList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return yemekIsimList.size
    }
}