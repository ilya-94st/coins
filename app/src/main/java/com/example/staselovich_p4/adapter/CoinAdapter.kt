package com.example.staselovich_p4.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.staselovich_p4.databinding.RecyclerBitcoinInformationBinding
import com.example.staselovich_p4.model.CoinModel
import com.squareup.picasso.Picasso

class CoinAdapter(): PagingDataAdapter<CoinModel, CoinAdapter.PagingViewHolder>(
    PHOTO_COMPARATOR) {
    inner class PagingViewHolder(private val binding: RecyclerBitcoinInformationBinding): RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun bind(coin: CoinModel) {
            binding.bitcoin = coin
            if(coin.quote?.USD?.percent_change_1h.toString()> 0.toString()){
                binding.textHour.setTextColor(Color.GREEN)
            } else {
                binding.textHour.setTextColor(Color.RED)
            }
            if (coin.quote?.USD?.percent_change_24h.toString()>0.toString()){
                binding.text24.setTextColor(Color.GREEN)
            } else {
                binding.text24.setTextColor(Color.RED)
            }
            if (coin.quote?.USD?.percent_change_7d.toString()>0.toString()){
                binding.textView7.setTextColor(Color.GREEN)
            } else {
                binding.textView7.setTextColor(Color.RED)
            }
        }
    }

    companion object{
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<CoinModel>(){
            override fun areItemsTheSame(oldItem: CoinModel, newItem: CoinModel) =
                oldItem.id == newItem.id
            override fun areContentsTheSame(
                oldItem: CoinModel,
                newItem: CoinModel
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
        val binding = RecyclerBitcoinInformationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PagingViewHolder(binding)
    }
}