package io.github.backjeff.chucknorrisjokes.base.extensions

import android.view.View

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
