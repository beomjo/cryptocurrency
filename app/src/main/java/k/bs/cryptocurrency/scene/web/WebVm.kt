package k.bs.cryptocurrency.scene.web

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

class WebVm {
    val url = ObservableField<String>()
    val isLoadingEnd = ObservableBoolean()
    val loadingListener = object : Loading {
        override fun loadingStart() {
            isLoadingEnd.set(false)
        }

        override fun loadingComplete() {
            isLoadingEnd.set(true)
        }

        override fun loadingError() {
            isLoadingEnd.set(true)
        }
    }

    fun init(url: String) {
        this.url.set(url)
    }
}