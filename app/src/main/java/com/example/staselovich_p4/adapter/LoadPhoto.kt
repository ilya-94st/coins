package com.example.staselovich_p4.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.staselovich_p4.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.NumberFormatException


@BindingAdapter("loadImage")
fun CircleImageView.loadImage(profilePhot: Int){
    Picasso.get()
        .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + profilePhot + ".png").into(this)

}


@SuppressLint("SetTextI18n")
@BindingAdapter("textRetrodaction")
fun TextView.textRetrodaction(number: Double){
    text =  (Math.round(number * 100.0) / 100.0).toString() + " % "
}

@SuppressLint("SetTextI18n")
@BindingAdapter("textRetrodaction2")
fun TextView.textRetrodaction2(number: Double){
    text =  (Math.round(number * 100.0) / 100.0).toString()
}



@BindingAdapter("bindPhoto")
fun CircleImageView.bindPhoto(photo: Any) {
    if(photo is Drawable) setImageDrawable(photo)
    else if(photo is Int) {
        Picasso.get()
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + photo + ".png").into(this)
    }
}
