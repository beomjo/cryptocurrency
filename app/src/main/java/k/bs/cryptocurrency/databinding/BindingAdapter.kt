package k.bs.cryptocurrency.databinding

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import k.bs.cryptocurrency.scene.web.Loading


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
        @BindingAdapter("url","loadingListener")
        fun webLoad(webView: WebView, url: String,loadingListener: Loading? = null) {
            webView.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    loadingListener?.loadingStart()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    loadingListener?.loadingComplete()
                }

                override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
                ) {
                    Toast.makeText(
                        webView.context,
                        "페이지 로딩에 실패하였습니다.[${errorCode}]",
                        Toast.LENGTH_LONG
                    ).show()
                    loadingListener?.loadingError()
                    super.onReceivedError(view, errorCode, description, failingUrl)
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url)
                    return true
                }
            }

            webView.settings.apply {
                javaScriptEnabled = true // 웹페이지 자바스크립트 허용 여부
                setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
                javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
                loadWithOverviewMode = true // 메타태그 허용 여부
                useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
                setSupportZoom(false) // 화면 줌 허용 여부
                builtInZoomControls = false // 화면 확대 축소 허용 여부
                layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
                cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
            }
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

            webView.webChromeClient = WebChromeClient()

            webView.loadUrl(url)
        }
    }
}
