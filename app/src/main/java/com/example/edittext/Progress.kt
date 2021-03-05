package com.example.edittext

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Progress @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) :
    View(context, attrs, defStyleAttr) {


    private var mLength = 0f
    private var dashes = floatArrayOf(0.0f, 60000f)
    private var mRadius = 200f
    private var mRect = RectF(10f, 10f, width.toFloat() - 10, height.toFloat() - 10)

    private var mPaint: Paint = Paint().apply {
        strokeWidth = 15f
        color = ContextCompat.getColor(context, R.color.black)
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }


    private var mPath = Path()
    private var mPaintBackground: Paint = Paint().apply {
        strokeWidth = 15f
        color = ContextCompat.getColor(context, R.color.purple_500)
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mRect = RectF(10f, 10f, w.toFloat() - 10, h.toFloat() - 10)
        mPath.addRoundRect(mRect, mRadius, mRadius, Path.Direction.CW)
        mLength = PathMeasure(mPath, false).length
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawPath(mPath, mPaintBackground)
            drawRoundRect(mRect, mRadius, mRadius, mPaint)
        }
    }

    fun startAnimation(mDuration: Long) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(200)
            ValueAnimator.ofInt(0, (mLength ?: 0).toInt()).apply {
                duration = mDuration
                addUpdateListener { animation ->
                    dashes[0] = animation.animatedValue.toString().toFloat()
                    mPaint.pathEffect = DashPathEffect(dashes, 0f)
                    invalidate()
                }
                start()
            }
        }
    }
}