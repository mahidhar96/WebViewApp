package com.mahidhar.webviewapp.main

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mahidhar.webviewapp.R
import com.mahidhar.webviewapp.databinding.MainFragmentBinding


class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        binding.mainWebView.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
//                binding.mainRefreshLayout.isRefreshing = true
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
//                binding.mainRefreshLayout.isRefreshing = false
            }
        }

        binding.mainWebView.requestFocus()
        binding.mainWebView.getSettings().setLightTouchEnabled(true)
        binding.mainWebView.settings.javaScriptEnabled = true
        binding.mainWebView.settings.domStorageEnabled = true
        binding.mainWebView.settings.useWideViewPort = true
        binding.mainWebView.settings.loadWithOverviewMode = true
        binding.mainWebView.settings.setGeolocationEnabled(true)
        binding.mainWebView.webChromeClient= object:WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress<100){

                }
            }

            override fun onGeolocationPermissionsShowPrompt(
                origin: String?,
                callback: GeolocationPermissions.Callback
            ) {
                callback.invoke(origin, true, false)
            }
        }
        binding.mainWebView.loadUrl(resources.getString(R.string.main_url))

        //refreshing the page
        //binding.mainRefreshLayout.setOnRefreshListener { binding.mainWebView.reload() }


    }

    fun onBackPressed(): Boolean {
        return if(binding.mainWebView.canGoBack()) {
            binding.mainWebView.goBack()
            binding.mainWebView.canGoBack()
        } else
            false
    }
}