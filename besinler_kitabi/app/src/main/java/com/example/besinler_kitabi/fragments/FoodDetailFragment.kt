package com.example.besinler_kitabi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.besinler_kitabi.databinding.FragmentFoodDetailBinding
import com.example.besinler_kitabi.viewmodel.FoodDetailViewModel


class FoodDetailFragment : Fragment() {

    private lateinit var foodDetailFragmentBinding: FragmentFoodDetailBinding
    private lateinit var foodDetailViewModel: FoodDetailViewModel
    private var foodIdArg: Long? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        foodDetailFragmentBinding = FragmentFoodDetailBinding.inflate(layoutInflater)
        return foodDetailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        arguments?.let {
            foodIdArg = FoodDetailFragmentArgs.fromBundle(it).foodId
        }

        foodDetailViewModel = ViewModelProvider(this).get(FoodDetailViewModel::class.java)
        foodIdArg?.let { foodDetailViewModel.readDetailFromSqlite(it) }

        observeDetailData()

    }

    private fun observeDetailData() {
        foodDetailViewModel.foodDetailLiveData.observe(viewLifecycleOwner, Observer {
            it.let {

                foodDetailFragmentBinding.food = it

                /*
                foodDetailFragmentBinding.foodDetailNameTextView.text = it.foodName
                foodDetailFragmentBinding.foodDetailCalorieTextView.text = it.foodCalorie
                foodDetailFragmentBinding.foodDetailCarbohydrateTextView.text = it.foodCarbonhydrate
                foodDetailFragmentBinding.foodDetailProteinTextView.text = it.foodProtein
                foodDetailFragmentBinding.foodDetailOilTextView.text = it.foodOil

                context?.let {context->
                    foodDetailFragmentBinding.foodDetailImageView.downloadImage(it.foodImage, makePlaceholder(context))
                }

                 */

            }
        })
    }

}