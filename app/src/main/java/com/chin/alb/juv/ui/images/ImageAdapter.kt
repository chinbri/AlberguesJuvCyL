package com.chin.alb.juv.ui.images

import androidx.viewpager.widget.PagerAdapter
import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.chin.alb.juv.R
import com.squareup.picasso.Picasso


class ImageAdapter(val context: Context, val images: List<String>): PagerAdapter() {

    private val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val myImageLayout = inflater.inflate(R.layout.slide, view, false)
        val myImage = myImageLayout
            .findViewById(R.id.image) as ImageView

        Picasso.get().load(images[position]).into(myImage)

        view.addView(myImageLayout, 0)
        return myImageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}