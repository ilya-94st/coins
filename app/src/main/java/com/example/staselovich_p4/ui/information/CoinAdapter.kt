package com.example.staselovich_p4.ui.information

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.staselovich_p4.databinding.RecyclerBitcoinInformationBinding
import com.example.staselovich_p4.model.CoinModel

class CoinAdapter( private val listener: OnItemClick): PagingDataAdapter<CoinModel, CoinAdapter.PagingViewHolder>(
    COIN_COMPARATOR
) {
    inner class PagingViewHolder(private val binding: RecyclerBitcoinInformationBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(coin: CoinModel) {
            binding.bitcoin = coin
            binding.imagefavorite.setOnClickListener {
                val coinPosition = bindingAdapterPosition
                if (coinPosition != RecyclerView.NO_POSITION) {
                    val item = getItem(coinPosition)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }
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
    interface OnItemClick {
        fun onItemClick(coin: CoinModel)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem!=null) {
            holder.bind(currentItem)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val binding = RecyclerBitcoinInformationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PagingViewHolder(binding)
    }
}