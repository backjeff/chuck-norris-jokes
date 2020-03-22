package io.github.backjeff.chucknorrisjokes.base.core

import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import io.github.backjeff.chucknorrisjokes.base.R
import io.github.backjeff.chucknorrisjokes.base.dialog.LoadingDialog
import io.github.backjeff.chucknorrisjokes.base.extensions.navDirections
import io.github.backjeff.chucknorrisjokes.base.extensions.setNavigationIcon
import org.jetbrains.anko.alert
import org.jetbrains.anko.findOptional
import org.jetbrains.anko.yesButton
import org.koin.core.KoinComponent

abstract class BaseFragment : Fragment(), ViewStateListener, KoinComponent {
    private val baseNavigation: BaseFragmentNavigation by navDirections()

    private var toolbar: Toolbar? = null
    private val loadingDialogFragment = LoadingDialog()
    private var alertDialog: AlertDialog? = null

    open fun addObservers(owner: LifecycleOwner) {}

    open fun setupView() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addObservers(viewLifecycleOwner)
    }

    private fun setupToolbar() {
        toolbar = requireView().findOptional<Toolbar>(R.id.appToolbar)?.also {
            (requireActivity() as? AppCompatActivity)?.apply {
                setSupportActionBar(it)
                setupActionBarWithNavController(requireView().findNavController())
                setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> try {
                findNavController().popBackStack()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onStateError(error: Throwable) {
        hideLoading()
        error.message?.let { showAlert(message = it) }
    }

    override fun onStateLoading() {
        hideLoading()
        fragmentManager?.let { loadingDialogFragment.show(it, "") }
    }

    override fun hideLoading() {
        loadingDialogFragment.dismissAllowingStateLoss()
    }

    fun showAlert(title: String? = null, message: String, dismissAction: () -> Unit = {}) {
        alertDialog?.dismiss()
        requireContext().alert(
            title = title ?: getString(R.string.default_dialog_title),
            message = message
        ) {
            yesButton { }
        }.show().also {
            it.setOnDismissListener { dismissAction() }
            alertDialog = it
        }
    }

    fun setToolbarButtonVisible(enabled: Boolean) {
        (requireActivity() as? AppCompatActivity)?.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
        }
    }

}
