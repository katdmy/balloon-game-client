package com.katdmy.android.balloon_game_client.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.getDimensionOrThrow
import androidx.core.content.withStyledAttributes
import com.katdmy.android.balloon_game_client.R


@Suppress("ConvertSecondaryConstructorToPrimary")
class CustomSnivel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val mBackgroundPaint by lazy {
        Paint().apply {
            alpha = 0
            isAntiAlias = true
            color = Color.GREEN
            style = Paint.Style.FILL
        }
    }

    private val mPath = Path()

    private var snivelX: Float = 0f
    private var snivelY: Float = 0f

    private var diffs = 0.025

    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.CustomSnivel,
            defStyleAttr,
            DEF_STYLE_RES
        ) {
            snivelX = getDimensionOrThrow(R.styleable.CustomSnivel_snivelX)
            snivelY = getDimensionOrThrow(R.styleable.CustomSnivel_snivelY)
        }

        setWillNotDraw(false)
        initElements()
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        doLayout()
        canvas.drawPath(mPath, mBackgroundPaint)
        canvas.clipPath(mPath)
    }

    private fun doLayout() {
        val left = paddingLeft + snivelX
        val right = (width - paddingRight) * diffs
        val top = paddingTop
        val bottom = height - paddingBottom

        val center = ((top + bottom) / 2) - snivelY
        val radius = (height / 2) * diffs

        mPath.reset()

        mPath.moveTo(left, center)

        mPath.lineTo((right - radius + snivelX).toFloat(), ((center - radius).toFloat()))

        mPath.arcTo(
            (right + snivelX - (radius * 2)).toFloat(),
            ((center - radius).toFloat()),
            (right + snivelX.toFloat()).toFloat(),
            ((center + radius).toFloat()),
            -90f,
            180f,
            false
        )

        mPath.lineTo((right - radius + snivelX).toFloat(), ((center + radius).toFloat()))

        mPath.close()
    }

    private fun initElements() {
        invalidate()
    }

    fun increaseSnivel() {
        val corrector = (((width - snivelX) * 100) / width) / 100
        if (diffs < corrector) {
            diffs += 0.025
            invalidate()
        }
    }

    fun clearSnivel() {
        diffs = 0.05
        invalidate()
    }

    companion object {
        private const val DEF_STYLE_RES = R.style.CustomSnivel
    }
}