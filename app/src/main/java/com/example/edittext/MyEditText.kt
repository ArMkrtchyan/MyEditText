package com.example.edittext

import android.content.Context
import android.text.InputType.TYPE_CLASS_PHONE
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.edittext.databinding.LayoutEditTextBinding
import com.hbb20.CCPCountry


class MyEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private var textHint: CharSequence

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        val mBinding = LayoutEditTextBinding.inflate(LayoutInflater.from(context), this, false)
        val incomingValues = context.obtainStyledAttributes(attrs, R.styleable.MyEditText)
        addView(
            mBinding.root,
            layoutParams
        )
        val text = incomingValues.getText(R.styleable.MyEditText_text)
        textHint = incomingValues.getText(R.styleable.MyEditText_hint)
        val textColor =
            incomingValues.getColor(
                R.styleable.MyEditText_textColor,
                ContextCompat.getColor(context, android.R.color.white)
            )
        val textSize =
            incomingValues.getDimension(
                R.styleable.MyEditText_textSize,
                context.resources.getDimension(R.dimen.text_size)
            )
        val textHintColor =
            incomingValues.getColor(
                R.styleable.MyEditText_hintColor,
                ContextCompat.getColor(context, android.R.color.white)
            )

        val maxLines =
            incomingValues.getInt(
                R.styleable.MyEditText_maxLines,
                -1
            )
        if (maxLines != -1) {
            mBinding.input.maxLines = maxLines
        }

        val minLines =
            incomingValues.getInt(
                R.styleable.MyEditText_minLines,
                -1
            )
        if (minLines != -1) {
            mBinding.input.minLines = minLines
        }

        val singleLine =
            incomingValues.getBoolean(
                R.styleable.MyEditText_singleLine,
                false
            )
        if (singleLine) mBinding.input.isSingleLine = singleLine

        val gravity =
            incomingValues.getInt(
                R.styleable.MyEditText_gravity,
                Gravity.START
            )
        mBinding.input.gravity = gravity
        val inputType: Int =
            incomingValues.getInt(R.styleable.MyEditText_inputType, EditorInfo.TYPE_NULL)
        if (inputType != EditorInfo.TYPE_NULL) {
            mBinding.input.inputType = inputType
        }
        when (inputType) {
            TYPE_CLASS_PHONE -> {
                mBinding.code.visibility = View.VISIBLE
            }
            129 -> {
                mBinding.showHide.visibility = View.VISIBLE
            }
            18 -> {
                mBinding.showHide.visibility = View.VISIBLE
            }
            145 -> {
                mBinding.showHide.visibility = View.VISIBLE
            }
            225 -> {
                mBinding.showHide.visibility = View.VISIBLE
            }
        }
        mBinding.input.setText(text)
        mBinding.input.setHint(textHint)
        mBinding.input.setHintTextColor(textHintColor)
        mBinding.input.setTextColor(textColor)
        mBinding.input.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        incomingValues.recycle()
        invalidate()
        requestLayout()
        initClickListeners(mBinding)
    }

    private fun initClickListeners(mBinding: LayoutEditTextBinding) {
        var isShow = false
        mBinding.showHide.setOnClickListener {
            isShow = !isShow
            if (isShow) {
                mBinding.showHide.setImageResource(R.drawable.ic_eye_closed)
                mBinding.input.showPassword()
            } else {
                mBinding.showHide.setImageResource(R.drawable.ic_eye)
                mBinding.input.hidePassword()
            }
        }
        mBinding.code.setOnClickListener {

        }
    }
}