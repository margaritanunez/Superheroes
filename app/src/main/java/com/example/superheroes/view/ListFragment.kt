package com.example.superheroes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.superheroes.R
import com.example.superheroes.databinding.FragmentListBinding

class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    private val superheroeViewModel : SuperheroeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        initAdapter()
        superheroeViewModel.getAlllSuperheroes()
        return binding.root
    }

    private fun initAdapter() {
        val adapterSuperheroes= AdapterSuperheroes()
        binding.rvListSuperheroe.adapter = adapterSuperheroes
        superheroeViewModel.superheroeLiveData().observe(viewLifecycleOwner){
            adapterSuperheroes.setData(it)
        }
    }

}