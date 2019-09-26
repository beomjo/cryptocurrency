package k.bs.cryptocurrency.databinding

import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun imageLoad(imageView: ImageView, imageUrl: String?) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("url")
        fun webLoad(webView: WebView, url: String) {
            webView.loadUrl(url)
        }
    }
}
