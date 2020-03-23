package io.github.backjeff.chucknorrisjokes.base.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import io.github.backjeff.chucknorrisjokes.base.R
import org.jetbrains.anko.find

class JokeCategoryListItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val button: AppCompatTextView

    var value: String = ""
        set(value) {
            field = value
            button.text = value.capitalize()
        }

    init {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        LayoutInflater.from(context)
            .inflate(R.layout.custom_joke_category_item, this, true).run {
                button = find(R.id.customJokeCategoryListItemButton)
            }
    }

    fun setupItem(value: String) {
        this.value = value
    }

}