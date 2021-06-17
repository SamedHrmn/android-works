package com.example.yemek_tarifleri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemek_tarifleri.databinding.FragmentYemekListeBinding

class YemekListeFragment : Fragment() {

    private lateinit var recyclerAdapter: ListeFragmentRecyclerAdapter
    private lateinit var binding: FragmentYemekListeBinding
    private lateinit var dbHelper: DatabaseHelper


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentYemekListeBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            dbHelper = DatabaseHelper(it)
        }

        var myList = ArrayList<String>()
        myList.add("SUCUK")

        recyclerAdapter = ListeFragmentRecyclerAdapter(dbHelper.yemekIsimList, dbHelper.yemekIdList)
        binding.yemekListeRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.yemekListeRecyclerView.adapter = recyclerAdapter

        dbHelper.showAllYemek(recyclerAdapter)
    }
}