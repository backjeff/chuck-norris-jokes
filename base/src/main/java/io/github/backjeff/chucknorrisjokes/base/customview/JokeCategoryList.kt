package io.github.backjeff.chucknorrisjokes.base.customview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class JokeCategoryList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private var listItems = listOf<Item>()
    private var listSelectorAdapter = ListSelectorAdapter()
    private var onItemSelected: (Int) -> Unit = {}

    init {
        adapter = listSelectorAdapter
        layoutManager = GridLayoutManager(context, 2)
    }

    fun addItems(list: List<String>, onItemSelected: (Int) -> Unit = {}) {
        listItems = list.map {
            Item(it)
        }
        this.onItemSelected = onItemSelected
        listSelectorAdapter.notifyDataSetChanged()
    }

    data class Item (
        val text: String
    )

    inner class ListSelectorAdapter : RecyclerView.Adapter<ListSelectorViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListSelectorViewHolder(
            JokeCategoryListItem(context)
        )

        override fun getItemCount(): Int = listItems.size

        override fun onBindViewHolder(holder: ListSelectorViewHolder, position: Int) {
            holder.bind(listItems[position], position)
        }
    }

    inner class ListSelectorViewHolder(val view: JokeCategoryListItem) : RecyclerView.ViewHolder(view) {

        fun bind(item: Item, position: Int) {
            view.setupItem(item.text)

            view.setOnClickListener {
                listSelectorAdapter.notifyDataSetChanged()
                onItemSelected(position)
            }
        }

    }

}
