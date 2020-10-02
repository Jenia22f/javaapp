package dev.vladmakarenko.financialPlanning.webview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import dev.vladmakarenko.financialPlanning.webview.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment() {

    companion object {
        const val URL_KEY = "url_key"
        private const val TAG = "WebViewFragment"
    }

    lateinit var binding: FragmentWebViewBinding

    private lateinit var url: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)

        url = arguments?.getString(URL_KEY) ?: ""

        with(binding.webView) {
            webViewClient = object : WebViewClient(){
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return false
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    return false
                }
            }
            isVerticalScrollBarEnabled = true

            with(settings) {
                javaScriptEnabled = true
                setSupportZoom(true)
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: $url")
        binding.webView.loadUrl("https://$url")
    }
}