package com.montcomp.yanata.Utils

import android.annotation.SuppressLint
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlin.math.abs

class DinamicScroll {/////no lo uso

    val SCROLL_THRESHOLD = 10f

    var showingTaskbar: HashMap<ChipNavigationBar, Boolean> = hashMapOf()
    var x = 0f
    var y = 0f

    fun setHidingToolbar(view: View, toolbar: ChipNavigationBar) {
        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    x = event.x
                    y = event.y
                }

                MotionEvent.ACTION_UP -> {
                    if (abs(x - event.x) < SCROLL_THRESHOLD || abs(y - event.y) < SCROLL_THRESHOLD){
                        toggleToolbar(toolbar)
                    }
                }
            }
            v.performClick()
            v.onTouchEvent(event) ?: true
        }
    }

    @SuppressLint("NewApi")
    fun toggleToolbar(toolbar: ChipNavigationBar) {
        if ( showingTaskbar.getOrDefault(toolbar, false)) {

            // Toggle tracker for displaying toolbar
            showingTaskbar[toolbar] = false

            // Extract the height of the action bar
            val tv = TypedValue()
            var actionBarHeight = 0
            if (toolbar.context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, toolbar.context.resources.displayMetrics)
            }

            // Initiate the animation
            toolbar.animate().translationY(-actionBarHeight.toFloat()).setInterpolator(AccelerateInterpolator(2f)).start()
        } else {
            // Toggle tracker for displaying toolbar
            showingTaskbar[toolbar] = true

            // Initiate the animation
            toolbar.animate().translationY(0f).setInterpolator(DecelerateInterpolator(2f)).start()
        }
    }
}