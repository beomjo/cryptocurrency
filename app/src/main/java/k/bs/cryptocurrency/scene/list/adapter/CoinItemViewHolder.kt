package k.bs.cryptocurrency.scene.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import k.bs.cryptocurrency.model.ModelCoin

class CoinItemViewHolder(
    @LayoutRes layout: Int,
    parent: ViewGroup,
    private val bindingVariableId: Int?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(layout, parent, false)
) {
    val b: ViewDataBinding = DataBindingUtil.bind(itemView)!!

    fun onBind(item: CoinItemVm?) {
        if (bindingVariableId == null)
            return

        b.setVariable(bindingVariableId, item)
        itemView.visibility = View.VISIBLE

        b.executePendingBindings()
    }

}