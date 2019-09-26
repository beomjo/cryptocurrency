package k.bs.cryptocurrency.base

import android.view.MenuItem
import androidx.databinding.ViewDataBinding

abstract class BaseActionBarActivity<V : ViewDataBinding, VM : Any> : BaseBindingActivity<V, VM>() {

    protected fun setActionBarTitleAndBackBtn(title: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    protected fun hideActionBar(){
        supportActionBar?.hide()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return true
    }
}