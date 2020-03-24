package io.github.backjeff.chucknorrisjokes.base.extensions

import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation

fun View.setGone() {
    visibility = View.GONE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun withViews(vararg views: View, action: (View) -> Any) {
    views.forEach {
        action(it)
    }
}

fun View.rotate() {
    startAnimation(
        RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).also {
            it.repeatCount = Animation.INFINITE
            it.duration = 1000
            it.interpolator = LinearInterpolator()
        }
    )
}
