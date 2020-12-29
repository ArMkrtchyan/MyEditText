package com.example.edittext

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.edittext.databinding.LayoutToolbarBinding

class MyToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {
    private val mBinding = LayoutToolbarBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val incomingValues = context.obtainStyledAttributes(attrs, R.styleable.MyToolbar)
        addView(
            mBinding.root,
            layoutParams
        )
        mBinding.title.text = incomingValues.getString(R.styleable.MyToolbar_title)
    }

    fun getRoot(): androidx.appcompat.widget.Toolbar {
        return mBinding.root
    }
}