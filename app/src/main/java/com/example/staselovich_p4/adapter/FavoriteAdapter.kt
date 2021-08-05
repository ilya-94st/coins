package com.example.staselovich_p4.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.staselovich_p4.dataBase.CoinEntity
import com.example.staselovich_p4.databinding.RecyclerFavoritesBinding


class FavoriteAdapter: ListAdapter<CoinEntity, FavoriteAdapter.PagingViewHolder>(
    PHOTO_COMPARATOR) {
    inner class PagingViewHolder(private val binding: RecyclerFavoritesBinding): RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun bind(coin: CoinEntity) {
            binding.coinDatabase = coin
        }
    }

    companion object{
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<CoinEntity>(){
            override fun areItemsTheSame(oldItem: CoinEntity, newItem: CoinEntity) =
                oldItem.id == newItem.id
            override fun areContentsTheSame(
                oldItem: CoinEntity,
                newItem: CoinEntity
            ) = oldItem.id == newItem.id

        }
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem!=null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val binding = RecyclerFavoritesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PagingViewHolder(binding)
    }


}