package dev.vladmakarenko.financialPlanning.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.facebook.applinks.AppLinkData
import dev.vladmakarenko.financialPlanning.R
import dev.vladmakarenko.financialPlanning.core.App
import dev.vladmakarenko.financialPlanning.core.model.NetworkRequest
import dev.vladmakarenko.financialPlanning.core.repository.local.Storage
import dev.vladmakarenko.financialPlanning.core.repository.remote.NetworkService
import dev.vladmakarenko.financialPlanning.di.DaggerMainComponent
import dev.vladmakarenko.financialPlanning.webview.WebViewFragment
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    @Inject
    lateinit var storage: Storage

    @Inject
    lateinit var networkService: NetworkService

    override fun onCreate(savedInstanceState: Bundle?) {
        val coreComponent = (application as App).coreComponent
        DaggerMainComponent.factory().create(coreComponent).inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = storage.getUrl()

        AppLinkData.fetchDeferredAppLinkData(applicationContext) {
            when {
                url != null -> {
                    Log.d(TAG, "onCreate: $url not null")
                    val bundle = Bundle().apply {
                        putString(WebViewFragment.URL_KEY, url)
                    }

                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.main_nav_controller,
                            WebViewFragment::class.java,
                            bundle
                        )
                        .commit()
                }
                it?.targetUri != null -> {
                    Log.d(TAG, "onCreate: deferred deeplink")
                    val data = it.targetUri!!

                    val userAgent = WebSettings.getDefaultUserAgent(applicationContext)
                    val language = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        resources.configuration.locales[0].language.toUpperCase()
                    } else {
                        resources.configuration.locale.language.toUpperCase()
                    }
                    val _uri = (data.query ?: "")
                    Log.d(TAG, "onCreate: $_uri")
                    val utm = _uri

                    val networkRequest =
                        NetworkRequest(language = language, userAgent = userAgent, uTM = utm, app = "dana.app")

                    lifecycleScope.launch(IO) {
                        try {
                            val response = networkService.requestResponse(networkRequest)
                            Log.d(TAG, "onCreate: $response")
        //                    if (response.contains("true")) {
                            if (response.status) {
                                storage.setUrl(response.url!!)
                                withContext(Main) {
        //                            val firstIndex = response.indexOf("\"url\": \"")
        //                            val lastIndex = response.indexOf("\"", firstIndex)
                                    val bundle = Bundle().apply {
        //                                putString(WebViewFragment.URL_KEY, response.substring(firstIndex, lastIndex))
                                        putString(WebViewFragment.URL_KEY, response.url!!)
                                    }
                                    supportFragmentManager.beginTransaction()
                                        .replace(
                                            R.id.main_nav_controller,
                                            WebViewFragment::class.java,
                                            bundle
                                        )
                                        .commit()
                                }
                            }
                        } catch (e: Exception) {
                            Log.d(TAG, "onCreate: exception in request in main activity")
                        }
                    }

                }
                else -> {
                    Log.d(TAG, "onCreate: deeplink")
                    val userAgent = WebSettings.getDefaultUserAgent(applicationContext)
                    val language = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        resources.configuration.locales[0].language.toUpperCase()
                    } else {
                        resources.configuration.locale.language.toUpperCase()
                    }
                    val utm = intent?.data?.query

                    val networkRequest =
                        NetworkRequest(language = language, userAgent = userAgent, uTM = utm, app = "dana.app")
                    lifecycleScope.launch(IO) {
                        try {
                            val response = networkService.requestResponse(networkRequest)
                            Log.d(TAG, "onCreate: $response")
        //                    if (response.contains("true")) {
                            if (response.status) {
                                storage.setUrl(response.url!!)
                                withContext(Main) {
        //                            val firstIndex = response.indexOf("\"url\": \"")
        //                            val lastIndex = response.indexOf("\"", firstIndex)
                                    val bundle = Bundle().apply {
        //                                putString(WebViewFragment.URL_KEY, response.substring(firstIndex, lastIndex))
                                        putString(WebViewFragment.URL_KEY, response.url!!)
                                    }
                                    supportFragmentManager.beginTransaction()
                                        .replace(
                                            R.id.main_nav_controller,
                                            WebViewFragment::class.java,
                                            bundle
                                        )
                                        .commit()
                                }
                            }
                        } catch (e: Exception) {
                            Log.d(TAG, "onCreate: exception in request in main activity")
                        }
                    }
                }
            }
        }
    }
}

//override fun onCreate(savedInstanceState: Bundle?) {
//    val coreComponent = (application as App).coreComponent
//    DaggerMainComponent.factory().create(coreComponent).inject(this)
//
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
//    val url = storage.getUrl()
//
//    AppLinkData.fetchDeferredAppLinkData(applicationContext) {
//        Log.d(MainActivity.TAG, "onCreate: deferred deeplink")
//        if (url != null) {
//            Log.d(MainActivity.TAG, "onCreate: $url not null")
//            val bundle = Bundle().apply {
//                putString(WebViewFragment.URL_KEY, url)
//            }
//
//            supportFragmentManager.beginTransaction()
//                .replace(
//                    R.id.main_nav_controller,
//                    WebViewFragment::class.java,
//                    bundle
//                )
//                .commit()
//        } else {
//            val data = it?.targetUri ?: return@fetchDeferredAppLinkData
//
//            val userAgent = WebSettings.getDefaultUserAgent(applicationContext)
//            val language = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                resources.configuration.locales[0].language.toUpperCase()
//            } else {
//                resources.configuration.locale.language.toUpperCase()
//            }
//            val _uri = (data.query ?: "")
//            Log.d(MainActivity.TAG, "onCreate: $_uri")
//            val utm = _uri
//
//            val networkRequest =
//                NetworkRequest(language = language, userAgent = userAgent, uTM = utm)
//
//            lifecycleScope.launch(IO) {
//                try {
//                    val response = networkService.requestResponse(networkRequest)
//                    Log.d(MainActivity.TAG, "onCreate: $response")
////                    if (response.contains("true")) {
//                    if (response.status) {
//                        storage.setUrl(response.url!!)
//                        withContext(Main) {
////                            val firstIndex = response.indexOf("\"url\": \"")
////                            val lastIndex = response.indexOf("\"", firstIndex)
//                            val bundle = Bundle().apply {
////                                putString(WebViewFragment.URL_KEY, response.substring(firstIndex, lastIndex))
//                                putString(WebViewFragment.URL_KEY, response.url!!)
//                            }
//                            supportFragmentManager.beginTransaction()
//                                .replace(
//                                    R.id.main_nav_controller,
//                                    WebViewFragment::class.java,
//                                    bundle
//                                )
//                                .commit()
//                        }
//                    }
//                } catch (e: Exception) {
//                    Log.d(MainActivity.TAG, "onCreate: exception in request in main activity")
//                }
//            }
//
//        }
//    }
//    if (url != null) {
//        Log.d(MainActivity.TAG, "onCreate: $url not null")
//        val bundle = Bundle().apply {
//            putString(WebViewFragment.URL_KEY, url)
//        }
//
//        supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.main_nav_controller,
//                WebViewFragment::class.java,
//                bundle
//            )
//            .commit()
//    } else {
//        val userAgent = WebSettings.getDefaultUserAgent(applicationContext)
//        val language = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            resources.configuration.locales[0].language.toUpperCase()
//        } else {
//            resources.configuration.locale.language.toUpperCase()
//        }
//        val utm = intent?.data?.query
//
//        val networkRequest =
//            NetworkRequest(language = language, userAgent = userAgent, uTM = utm)
//
//        lifecycleScope.launch(IO) {
//            try {
//                val response = networkService.requestResponse(networkRequest)
//                Log.d(MainActivity.TAG, "onCreate: $response")
////                    if (response.contains("true")) {
//                if (response.status) {
//                    storage.setUrl(response.url!!)
//                    withContext(Main) {
////                            val firstIndex = response.indexOf("\"url\": \"")
////                            val lastIndex = response.indexOf("\"", firstIndex)
//                        val bundle = Bundle().apply {
////                                putString(WebViewFragment.URL_KEY, response.substring(firstIndex, lastIndex))
//                            putString(WebViewFragment.URL_KEY, response.url!!)
//                        }
//                        supportFragmentManager.beginTransaction()
//                            .replace(
//                                R.id.main_nav_controller,
//                                WebViewFragment::class.java,
//                                bundle
//                            )
//                            .commit()
//                    }
//                }
//            } catch (e: Exception) {
//                Log.d(MainActivity.TAG, "onCreate: exception in request in main activity")
//            }
//        }
//    }