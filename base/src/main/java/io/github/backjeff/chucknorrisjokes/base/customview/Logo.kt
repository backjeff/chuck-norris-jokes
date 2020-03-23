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

    fun blink(action: () -> Unit = {}) {
        CoroutineScope(Dispatchers.IO).launch() {
            delay(500)
            setImageResource(R.drawable.logo_blink)
            blinkSound.start()
            delay(100)
            setImageResource(R.drawable.logo)
            delay(500)
            setImageResource(R.drawable.logo_blink)
            blinkSound.start()
            delay(100)
            setImageResource(R.drawable.logo)
            delay(1000)
            action()
        }

    }

}
