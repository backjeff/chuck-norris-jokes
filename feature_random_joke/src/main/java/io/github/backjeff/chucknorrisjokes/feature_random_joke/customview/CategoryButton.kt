package io.github.backjeff.chucknorrisjokes.feature_random_joke.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import io.github.backjeff.chucknorrisjokes.base.R

class CategoryButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatButton(context, attrs, defStyle) {

    var closeState: Boolean = false
        set(value) {
            field = value
            if(value) {
                setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_close_small,0)
            } else {
                setCompoundDrawablesWithIntrinsicBounds(0,0, 0,0)
            }
        }

}
