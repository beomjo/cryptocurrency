package k.bs.cryptocurrency.scene.web

import androidx.databinding.ObservableField

class WebVm {
    val url = ObservableField<String>()
    fun init(url: String) {
        this.url.set(url)
    }
}