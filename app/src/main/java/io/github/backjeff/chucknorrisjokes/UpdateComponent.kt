package io.github.backjeff.chucknorrisjokes

import android.content.Context
import android.util.Log
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.common.IntentSenderForResultStarter
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task

class UpdateComponent(private val updateParameter: UpdateParameter) {

    private lateinit var manager: AppUpdateManager
    private lateinit var updatedListener: InstallStateUpdatedListener

    private fun getAppUpdateTypeSupported() = AppUpdateType.IMMEDIATE

    fun startUpdate(applicationContext: Context) {
        checkForUpdates(applicationContext)
    }

    private fun checkForUpdates(applicationContext: Context) {
        manager = AppUpdateManagerFactory.create(applicationContext)
        val appUpdateInfo = manager.appUpdateInfo

        appUpdateInfo.addOnSuccessListener {
            handleUpdate(manager, appUpdateInfo)
        }
    }

    private fun handleUpdate(manager: AppUpdateManager, info: Task<AppUpdateInfo>) {
        if (getAppUpdateTypeSupported() == AppUpdateType.IMMEDIATE) {
            handleImmediateUpdate(manager, info)
        } else if (getAppUpdateTypeSupported() == AppUpdateType.FLEXIBLE) {
            handleFlexibleUpdate(manager, info)
        }
    }

    private fun handleImmediateUpdate(manager: AppUpdateManager, info: Task<AppUpdateInfo>) {
        if ((info.result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE ||
                    info.result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS)
            && info.result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
        ) {
            manager.startUpdateFlowForResult(
                info.result,
                AppUpdateType.IMMEDIATE,
                updateParameter.starter,
                333
            )
        }
    }

    private fun handleFlexibleUpdate(manager: AppUpdateManager, info: Task<AppUpdateInfo>) {
        if (info.result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
            && info.result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
        ) {
            handleImmediateUpdate(manager, info)
            return
        }

        if ((info.result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE ||
                    info.result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS)
            && info.result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
        ) {
            setUpdateAction(manager, info)
        }
    }

    private fun setUpdateAction(manager: AppUpdateManager, info: Task<AppUpdateInfo>) {
        updatedListener = InstallStateUpdatedListener {
            when (it.installStatus()) {
                InstallStatus.FAILED, InstallStatus.UNKNOWN -> {
                    Log.d("STATUS", "Failed")
                }
                InstallStatus.PENDING -> {
                    Log.d("STATUS", "Pending")
                }
                InstallStatus.CANCELED -> {
                    Log.d("STATUS", "Canceled")
                }
                InstallStatus.DOWNLOADING -> {
                    Log.d("STATUS", "Downloading")
                }
                InstallStatus.DOWNLOADED -> {
                    Log.d("STATUS", "Installing")
                    //launchRestartDialog(manager)
                }
                InstallStatus.INSTALLING -> {
                    Log.d("STATUS", "Installing")
                }
                InstallStatus.INSTALLED -> {
                    Log.d("STATUS", "Installed")
                    manager.unregisterListener(updatedListener)
                }
                else -> {
                    Log.d("STATUS", "Restart")
                }
            }
        }
        manager.registerListener(updatedListener)
        manager.startUpdateFlowForResult(
            info.result, AppUpdateType.FLEXIBLE, updateParameter.starter, 333
        )
    }
}

data class UpdateParameter(val starter: IntentSenderForResultStarter)

