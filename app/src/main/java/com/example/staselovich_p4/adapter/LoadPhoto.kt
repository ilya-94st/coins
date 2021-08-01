package com.example.staselovich_p4.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView



@BindingAdapter("loadImage")
fun CircleImageView.loadImage(profilePhot: Int){
    Picasso.get()
        .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + profilePhot + ".png")

}


@BindingAdapter("textRetrodaction")
fun TextView.textRetrodaction(text: Double): Double{

  return Math.round(text * 100.0) / 100.0
}

