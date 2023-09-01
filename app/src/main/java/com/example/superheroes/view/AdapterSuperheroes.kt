package com.example.superheroes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superheroes.data.local.SuperheroeEntity
import com.example.superheroes.databinding.ItemListBinding

class AdapterSuperheroes: RecyclerView.Adapter<AdapterSuperheroes.ItemListViewHolder>() {
    lateinit var binding : ItemListBinding
    private val listSuperheroes = mutableListOf<SuperheroeEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSuperheroes.size
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val superheroe= listSuperheroes[position]
        holder.bind(superheroe)
    }

    fun setData(superheroes: List<SuperheroeEntity>){
        this.listSuperheroes.clear()
        this.listSuperheroes.addAll(superheroes)
        notifyDataSetChanged()
    }
    class ItemListViewHolder (val superheroesBinding: ItemListBinding): RecyclerView.ViewHolder(superheroesBinding.root){
        fun bind (superheroe:SuperheroeEntity){
            val bundle = Bundle()
            superheroesBinding.tvName.text = superheroe.name
            superheroesBinding.imgSuperheroe.load(superheroe.imageLink)
        }
    }

}