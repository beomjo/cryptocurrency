package k.bs.cryptocurrency.scene.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import k.bs.cryptocurrency.R
import k.bs.cryptocurrency.paging.base.BaseDiffAdapter
import k.bs.cryptocurrency.paging.base.VIEW_TYPE_NORMAL
import k.bs.cryptocurrency.scene.list.CoinItemVm

class CoinAdapter(
    @LayoutRes val layout: Int,
    private val bindingVariableId: Int? = null
) : BaseDiffAdapter<CoinItemVm, RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_NORMAL)
            CoinItemViewHolder(
                layout = layout,
                parent = parent,
                bindingVariableId = bindingVariableId
            )
        else LoadingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_NORMAL)
            (holder as CoinItemViewHolder).onBind(getItem(position))
    }
}