package com.sonder.movies.ui.common

import android.content.Context
import android.util.AttributeSet
import com.sonder.movies.R

class AspectRatioImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    var ratio: Float = 1f

    init {
        val attrs = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
        ratio = attrs.getFloat(R.styleable.AspectRatioImageView_ratio, 1.5f)
        attrs.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var with = measuredWidth
        var height = measuredHeight
        if (with == 0 && height == 0){
            return
        }
        if (with > 0) {
            height = (with * ratio).toInt()
        }else if (height > 0) {
            with = ( height / ratio).toInt()
        }

        setMeasuredDimension(with, height)
    }

}