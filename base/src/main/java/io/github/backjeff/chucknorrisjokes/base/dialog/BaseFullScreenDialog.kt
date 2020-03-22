package io.github.backjeff.chucknorrisjokes.base.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import io.github.backjeff.chucknorrisjokes.base.R

open class BaseFullScreenDialog : BaseDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDialogStyle()
    }

    private fun setupDialogStyle() {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }


}