package io.github.backjeff.chucknorrisjokes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.common.IntentSenderForResultStarter
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task
import io.github.backjeff.chucknorrisjokes.intent.utils.safeNavigateUp

private const val REQUEST_UPDATE = 333

class AppActivity : AppCompatActivity(), IntentSenderForResultStarter {

    private lateinit var updateComponent: UpdateComponent

    private var appUpdateTypeSupported = AppUpdateType.IMMEDIATE

    private val navController by lazy { findNavController(R.id.baseContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        updateComponent = UpdateComponent(UpdateParameter(this))
        updateComponent.startUpdate(applicationContext)
    }

    override fun onSupportNavigateUp() = navController.safeNavigateUp()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (REQUEST_UPDATE == requestCode) {
            Log.d("UpdateResult", "Updated ended. Result code=${resultCode}")

            when (resultCode) {
                Activity.RESULT_OK -> {
                    if (appUpdateTypeSupported == AppUpdateType.IMMEDIATE) {
                        Toast.makeText(baseContext, "App successfully updated", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(baseContext, "App update started", Toast.LENGTH_SHORT).show()
                    }
                }
                Activity.RESULT_CANCELED -> {
                    Toast.makeText(baseContext, "App update cancelled", Toast.LENGTH_SHORT).show()
                }
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                    Toast.makeText(
                        baseContext,
                        "It was not possible to update your app",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
