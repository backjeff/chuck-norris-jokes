package io.github.backjeff.chucknorrisjokes.base.customview

import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import io.github.backjeff.chucknorrisjokes.base.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Logo @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    private val blinkSound = MediaPlayer.create(context, R.raw.blink)

    init {
        setImageResource(R.drawable.logo)
    }

    fun blink(sound: Boolean = true, action: () -> Unit = {}) {
        CoroutineScope(Dispatchers.Main).launch() {
            delay(500)
            setImageResource(R.drawable.logo_blink)
            playSound(sound)
            delay(100)
            setImageResource(R.drawable.logo)
            delay(400)
            setImageResource(R.drawable.logo_blink)
            playSound(sound)
            delay(100)
            setImageResource(R.drawable.logo)
            delay(1000)
            action()
        }

    }

    private fun playSound(sound: Boolean) {
        if (sound) blinkSound.start()
    }

}
