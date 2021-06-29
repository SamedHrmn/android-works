package com.example.besinler_kitabi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.besinler_kitabi.databinding.FoodRecyclerRowBinding
import com.example.besinler_kitabi.fragments.FoodListFragmentDirections
import com.example.besinler_kitabi.model.FoodModel

class FoodRecyclerAdapter(val foodList: ArrayList<FoodModel>) : RecyclerView.Adapter<FoodRecyclerAdapter.FoodRecyclerViewHolder>() {

    class FoodRecyclerViewHolder(val binding: FoodRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodRecyclerViewHolder {
        val binding = FoodRecyclerRowBinding.inflate(LayoutInflater.from(parent.context))
        return FoodRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodRecyclerViewHolder, position: Int) {

        holder.binding.food = foodList[position]

        /*
        holder.binding.foodNameTextView.text = foodList[position].foodName
        holder.binding.foodCalorieTextView.text = foodList[position].foodCalorie
        holder.binding.foodImageView.downloadImage(foodList[position].foodImage, makePlaceholder(holder.itemView.context))
        */

        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(foodId = foodList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun foodListNotifyChanged(foodList: ArrayList<FoodModel>) {
        this.foodList.clear()
        this.foodList.addAll(foodList)
        notifyDataSetChanged()

    }

}