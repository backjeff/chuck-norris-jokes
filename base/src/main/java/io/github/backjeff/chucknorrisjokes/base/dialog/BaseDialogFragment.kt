package io.github.backjeff.chucknorrisjokes.base.dialog

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.github.backjeff.chucknorrisjokes.base.core.BaseFragment

open class BaseDialogFragment : DialogFragment(), LifecycleObserver {

    private var isLoading = false
    private var fragment: BaseFragment? = null

    fun show(manager: FragmentManager) {
        show(manager, this::class.java.simpleName)
    }

    fun show(fragmentContainer: BaseFragment){
        this.fragment = fragmentContainer.apply {
            lifecycle.addObserver(this@BaseDialogFragment)
            show(fragmentContainer.childFragmentManager, this@BaseDialogFragment::class.java.simpleName)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun reset(){
        dismissAllowingStateLoss()
        this.fragment?.lifecycle?.removeObserver(this)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isAdded || isLoading) {
            isLoading = true
            try {
                super.show(manager, tag)
            } catch (e: Exception) { }
        }
    }

    override fun dismissAllowingStateLoss() {
        if (isLoading) {
            isLoading = false
            super.dismissAllowingStateLoss()
        }
    }
}