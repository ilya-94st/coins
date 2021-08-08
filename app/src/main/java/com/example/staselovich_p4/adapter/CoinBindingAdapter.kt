package com.example.staselovich_p4.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.roundToInt


@BindingAdapter("loadImage")
fun CircleImageView.loadImage(profilePhot: Int){
    Picasso.get()
        .load("https://s2.coinmarketcap.com/static/img/coins/64x64/$profilePhot.png").into(this)

}


@SuppressLint("SetTextI18n")
@BindingAdapter("textRetrodaction")
fun TextView.textRetrodaction(number: Double){
    text =  ((number * 100.0).roundToInt() / 100.0).toString() + " % "
}

@SuppressLint("SetTextI18n")
@BindingAdapter("textRetrodaction2")
fun TextView.textRetrodaction2(number: Double){
    text =  ((number * 100.0).roundToInt() / 100.0).toString()
}
@BindingAdapter("colorChoise")
fun TextView.colorChoise(number: Double) {
    if(number>0){
        setTextColor(Color.GREEN)
    } else{
        setTextColor(Color.RED)
    }
}

@BindingAdapter("bindPhoto")
fun CircleImageView.bindPhoto(photo: Any) {
    if(photo is Drawable) setImageDrawable(photo)
    else if(photo is Int) Picasso.get()
        .load("https://s2.coinmarketcap.com/static/img/coins/64x64/$photo.png").into(this)
}


