package com.example.retrofitproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitproject.R
import com.example.retrofitproject.databinding.ListItemBinding
import com.example.retrofitproject.retrofit.Product

class ProductAdapter : ListAdapter<Product, ProductAdapter.Holder>(Comporator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view){

        private val binding = ListItemBinding.bind(view)

        fun bind(product: Product) = with(binding){
            title.text = product.title
            description.text = product.description
        }

    }

    class Comporator : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

}