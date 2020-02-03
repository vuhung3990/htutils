package com.huutho.utilslib

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Build
import android.text.Layout
import android.view.View
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView


fun View.gone() = apply { if (visibility != View.GONE) visibility = View.GONE }

fun View.visible() = apply { if (visibility != View.VISIBLE) visibility = View.INVISIBLE }

fun View.invisible() = apply { if (visibility != View.INVISIBLE) visibility = View.INVISIBLE }

fun View.removeFromParentView() = run { (parent as ViewGroup).removeView(this) }

fun View.activeIf(condition: Boolean) = apply {
    isClickable = condition
    isFocusable = condition
    alpha = if (condition) 1f else 0.3f
}


/**
 * Value of dp to value of px.
 *
 * @param dpValue The value of dp.
 * @return value of px
 */
fun dpToPx(dpValue: Float): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}


/**
 * Value of px to value of dp.
 *
 * @param pxValue The value of px.
 * @return value of dp
 */
fun pxToDp(pxValue: Float): Float {
    return pxValue / Resources.getSystem().displayMetrics.density
}


/**
 * Value of sp to value of px.
 *
 * @param spValue The value of sp.
 * @return value of px
 */
fun spToPx(spValue: Float): Int {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

/**
 * Value of px to value of sp.
 *
 * @param pxValue The value of px.
 * @return value of sp
 */
fun pxToSp(pxValue: Float): Int {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5f).toInt()
}


/**
 * Return the width of view.
 *
 * @param view The view.
 * @return the width of view
 */
fun getMeasuredWidth(view: View): Int {
    return measureView(view)[0]
}

/**
 * Return the height of view.
 *
 * @param view The view.
 * @return the height of view
 */
fun getMeasuredHeight(view: View): Int {
    return measureView(view)[1]
}


/**
 * Measure the view.
 *
 * @param view The view.
 * @return arr[0]: view's width, arr[1]: view's height
 */
private fun measureView(view: View): IntArray {
    var lp: ViewGroup.LayoutParams? = view.layoutParams
    if (lp == null) {
        lp = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    val widthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width)
    val lpHeight = lp.height
    val heightSpec: Int
    if (lpHeight > 0) {
        heightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY)
    } else {
        heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    }
    view.measure(widthSpec, heightSpec)
    return intArrayOf(view.measuredWidth, view.measuredHeight)
}


fun getStatusBarHeight(): Int {
    val resources = Resources.getSystem()
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return resources.getDimensionPixelSize(resourceId)
}


fun getNavBarHeight(): Int {
    val res = Resources.getSystem()
    val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId != 0) {
        res.getDimensionPixelSize(resourceId)
    } else {
        0
    }
}


/**
 * Force get the size of view.
 *
 * e.g.
 * <pre>
 * SizeUtils.forceGetViewSize(view, new SizeUtils.onGetSizeListener() {
 * Override
 * public void onGetSize(final View view) {
 * view.getWidth();
 * }
 * });
</pre> *
 *
 * @param view     The view.
 * @param listener The get size listener.
 */
fun View.forceGetViewSize(listener: (view: View) -> Unit) {
    post { listener.invoke(this) }
}


fun getXLocationOnScreen(view: View): Int {
    val location = IntArray(2)
    view.getLocationOnScreen(location)
    return location[0]
}


fun getYLocationOnScreen(view: View): Int {
    val location = IntArray(2)
    view.getLocationOnScreen(location)
    return location[1]
}


fun getHeightTextViewBeforeRenderingLayout(context: Context, text: CharSequence, textSize: Int, deviceWidth: Int, typeface: Typeface, padding: Int): Int {
    val textView = TextView(context)
    textView.setPadding(padding, padding, padding, padding)
    textView.typeface = typeface
    textView.text = text
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        textView.breakStrategy = Layout.BREAK_STRATEGY_SIMPLE
    }
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
    val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST)
    val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    textView.measure(widthMeasureSpec, heightMeasureSpec)
    return textView.measuredHeight
}