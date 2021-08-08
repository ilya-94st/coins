package com.example.staselovich_p4.ui.converter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.staselovich_p4.databinding.ArrayCoinsBinding
import com.example.staselovich_p4.model.CoinModel

class CoinConverterAdapter(private val listener: OnItemClickListener): PagingDataAdapter<CoinModel, CoinConverterAdapter.PagingViewHolder>(
    COIN_COMPARATOR
) {
    inner class PagingViewHolder(private val binding: ArrayCoinsBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(coin: CoinModel) {
            binding.bitcoin = coin
        }

    }
    interface OnItemClickListener {
        fun onItemClick(coin: CoinModel)
    }

    companion object{
        private val COIN_COMPARATOR = object : DiffUtil.ItemCallback<CoinModel>(){
            override fun areItemsTheSame(oldItem: CoinModel, newItem: CoinModel) =
                oldItem.id == newItem.id
            override fun areContentsTheSame(
                oldItem: CoinModel,
                newItem: CoinModel
            ) = oldItem.id == newItem.id

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val binding = ArrayCoinsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem!=null) {
            holder.bind(currentItem)
        }
    }
}