package com.example.databinding_practice.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databinding_practice.R
import com.example.databinding_practice.adapter.RecyclerAdapter
import com.example.databinding_practice.databinding.FragmentListBinding
import com.example.databinding_practice.model.RecyclerItemModel


class ListFragment : Fragment() {
    private var itemList : ArrayList<RecyclerItemModel> = arrayListOf()
    private var recyclerAdapter: RecyclerAdapter = RecyclerAdapter(arrayListOf())
    private var _fragmentListBinding: FragmentListBinding? = null

    private val fragmentListBinding get() = _fragmentListBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentListBinding = FragmentListBinding.inflate(layoutInflater,container,false)
        return fragmentListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        for (i in 0..3){
            itemList.add(RecyclerItemModel(R.mipmap.ic_launcher,"Item $i"))
        }



        fragmentListBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        fragmentListBinding.recyclerView.adapter = recyclerAdapter
        recyclerAdapter.recyclerItems = itemList
        recyclerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentListBinding = null
    }


}