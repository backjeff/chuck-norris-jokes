package io.github.backjeff.chucknorrisjokes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import io.github.backjeff.chucknorrisjokes.intent.utils.safeNavigateUp

class AppActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.baseContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
    }

    override fun onSupportNavigateUp() = navController.safeNavigateUp()

}
