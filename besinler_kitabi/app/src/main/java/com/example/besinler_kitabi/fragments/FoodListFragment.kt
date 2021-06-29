package com.example.besinler_kitabi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.besinler_kitabi.adapter.FoodRecyclerAdapter
import com.example.besinler_kitabi.databinding.FragmentFoodListBinding
import com.example.besinler_kitabi.viewmodel.FoodListViewModel


class FoodListFragment : Fragment() {

    private lateinit var foodListFragmentBinding: FragmentFoodListBinding
    private lateinit var foodListViewModel: FoodListViewModel
    private var foodRecyclerAdapter: FoodRecyclerAdapter = FoodRecyclerAdapter(arrayListOf())


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        foodListFragmentBinding = FragmentFoodListBinding.inflate(layoutInflater)
        return foodListFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodListFragmentBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        foodListFragmentBinding.recyclerView.adapter = foodRecyclerAdapter

        foodListViewModel = ViewModelProvider(this).get(FoodListViewModel::class.java)

        foodListFragmentBinding.swipeRefreshLayout.setOnRefreshListener {
            foodListFragmentBinding.recyclerView.visibility = View.GONE
            foodListFragmentBinding.progressBar.visibility = View.VISIBLE
            foodListViewModel.getDataOnlyService()
            foodListFragmentBinding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    fun observeLiveData() {
        foodListViewModel.refreshData()

        foodListViewModel.foodListLiveData.observe(viewLifecycleOwner, Observer {
            it.let {
                foodListFragmentBinding.recyclerView.visibility = View.GONE
                foodListFragmentBinding.progressBar.visibility = View.VISIBLE
                foodRecyclerAdapter.foodListNotifyChanged(it)
                foodListFragmentBinding.recyclerView.visibility = View.VISIBLE
                foodListFragmentBinding.progressBar.visibility = View.GONE
            }
        })

        foodListViewModel.progressBarLiveData.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it) {
                    foodListFragmentBinding.progressBar.visibility = View.VISIBLE
                    foodListFragmentBinding.recyclerView.visibility = View.GONE
                } else {
                    foodListFragmentBinding.progressBar.visibility = View.GONE
                }
            }
        })


        foodListViewModel.errorTextLiveData.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it) {
                    foodListFragmentBinding.errorTextView.visibility = View.VISIBLE
                    foodListFragmentBinding.recyclerView.visibility = View.GONE
                } else {
                    foodListFragmentBinding.errorTextView.visibility = View.GONE
                }
            }
        })
    }


}