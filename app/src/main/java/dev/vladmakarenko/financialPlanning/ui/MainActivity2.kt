//package dev.vladmakarenko.financialPlanning.ui
//
//import android.os.Bundle
//import android.util.Log
//import android.webkit.WebSettings
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.lifecycleScope
//import dev.vladmakarenko.financialPlanning.R
//import dev.vladmakarenko.financialPlanning.core.App
//import dev.vladmakarenko.financialPlanning.core.model.NetworkRequest
//import dev.vladmakarenko.financialPlanning.core.repository.remote.NetworkService
//import dev.vladmakarenko.financialPlanning.di.DaggerMainComponent
//import dev.vladmakarenko.financialPlanning.webview.WebViewFragment
//import kotlinx.coroutines.Dispatchers.IO
//import kotlinx.coroutines.Dispatchers.Main
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import javax.inject.Inject
//
//class MainActivity2 : AppCompatActivity() {
//    companion object{
//        private const val TAG = "MainActivity"
//    }
//
//    @Inject
//    lateinit var networkService: NetworkService
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        val coreComponent = (application as App).coreComponent
//        DaggerMainComponent.factory().create(coreComponent).inject(this)
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val data = intent.data
//
//        data?.let {uri ->
//            val userAgent = WebSettings.getDefaultUserAgent(applicationContext)
//            val language = resources.assets.locales.first()
//            val _uri = (uri.path?:return@let)
//            val utm = _uri.substring(_uri.indexOfFirst { it == '?' })
//
//            val networkRequest = NetworkRequest(language = language, userAgent = userAgent, uTM = utm)
//
//            lifecycleScope.launch(IO){
//                val response = networkService.requestResponse(networkRequest)
//                Log.d(TAG, "onCreate: ${response.url}")
//                if (response.status){
//                    withContext(Main){
//                        val bundle = Bundle().apply {
//                            putString(WebViewFragment.URL_KEY, response.url!!)
//                        }
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_nav_controller, WebViewFragment::class.java, bundle)
//                            .commit()
//                    }
//                }
//            }
//        }
//    }
//}


//package dev.vladmakarenko.financialPlanning.ui
//
//import android.os.Bundle
//import android.util.Log
//import android.webkit.WebSettings
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.lifecycleScope
//import dev.vladmakarenko.financialPlanning.R
//import dev.vladmakarenko.financialPlanning.core.App
//import dev.vladmakarenko.financialPlanning.core.model.NetworkRequest
//import dev.vladmakarenko.financialPlanning.core.repository.remote.NetworkService
//import dev.vladmakarenko.financialPlanning.di.DaggerMainComponent
//import dev.vladmakarenko.financialPlanning.webview.WebViewFragment
//import kotlinx.coroutines.Dispatchers.IO
//import kotlinx.coroutines.Dispatchers.Main
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import javax.inject.Inject
//
//class MainActivity : AppCompatActivity() {
//    companion object {
//        private const val TAG = "MainActivity"
//    }
//
//    @Inject
//    lateinit var networkService: NetworkService
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        val coreComponent = (application as App).coreComponent
//        DaggerMainComponent.factory().create(coreComponent).inject(this)
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
////        val userAgent = WebSettings.getDefaultUserAgent(applicationContext)
////        val language = resources.assets.locales.first()
////        val utm = "apsubid1=1&apsubid2=2&apsubid3=3&apsubid4=4&apsubid5=5"
////
////        val networkRequest = NetworkRequest(language = language, userAgent = userAgent, uTM = utm)
////
////        lifecycleScope.launch(IO) {
////            val response = networkService.requestResponse(networkRequest)
////            Log.d(TAG, "onCreate: ${response.url}")
////            if (response.status) {
////                withContext(Main) {
////                    val bundle = Bundle().apply {
////                        putString(WebViewFragment.URL_KEY, response.url!!)
////                    }
////                    supportFragmentManager.beginTransaction()
////                        .replace(R.id.main_nav_controller, WebViewFragment::class.java, bundle)
////                        .commit()
////                }
////            }
////        }
//
//    }
//}