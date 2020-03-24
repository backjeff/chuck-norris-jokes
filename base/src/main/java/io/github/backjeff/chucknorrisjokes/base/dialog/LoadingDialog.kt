package io.github.backjeff.chucknorrisjokes.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.backjeff.chucknorrisjokes.base.R
import io.github.backjeff.chucknorrisjokes.base.extensions.rotate
import kotlinx.android.synthetic.main.dialog_loading.*


class LoadingDialog : BaseFullScreenDialog() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingDialogProgress.rotate()

    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.run{
            dialog?.setCancelable(false)
            setBackgroundDrawableResource(R.color.colorWhiteTranslucid)
            attributes = attributes.run {
                dimAmount = 0f
                this
            }

        }
    }

}
