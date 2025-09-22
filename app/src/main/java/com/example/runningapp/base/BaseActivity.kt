package com.example.runningapp.base

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.example.runningapp.AppStateListener
import com.example.runningapp.MainActivity
import com.example.runningapp.MyApplication
import com.example.runningapp.utils.Common
import com.example.runningapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

typealias Inflate<T> = (LayoutInflater) -> T

@Suppress("DEPRECATION")
abstract class BaseActivity<T : ViewBinding>(private val inflater: Inflate<T>) :
    AppCompatActivity(), AppStateListener {
    protected val binding: T by lazy { inflater(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        val isDarkMode = false
        val flag: Int
        Log.d(TAG, "isDarkMode $isDarkMode")
        Common.setLocale(this@BaseActivity, Common.getPreLanguage(this))
        if (isDarkMode) {
            setTheme(R.style.Theme_RunningApp)
            flag =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        } else {
            setTheme(R.style.Theme_RunningApp)
            flag =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // Force light theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Initialize lastKnownUiMode
        lastKnownUiMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        super.onCreate(savedInstanceState)
        MyApplication.getInstance().registerAppStateListener(this)

        window.decorView.systemUiVisibility = flag

        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if ((visibility and View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            )

                    val isInDarkMode = false
                    val fl = if (isInDarkMode) {
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    } else {
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    }
                    window.decorView.systemUiVisibility = fl
                }
            }
        }

        window.navigationBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.TRANSPARENT
        setContentView(binding.root)
    }


//    open fun openFragment(fragment: Fragment) {
//        val manager: FragmentManager = supportFragmentManager
//        val transaction: FragmentTransaction = manager.beginTransaction()
//        transaction.replace(R.id.frameLayout,fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val systemUiMode = newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val oldUiMode = 0
        Log.d(TAG, "System UI mode changed from $oldUiMode to $systemUiMode")

        if (oldUiMode != systemUiMode) {
            // Save new value
            lastKnownUiMode = systemUiMode

            // Restart app or move to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getInstance().unregisterAppStateListener(this)
    }

    override fun onAppReturnedToForeground() {
        val totalTime = (System.currentTimeMillis() - now) / 1000L
        println("${this::class.java.simpleName}: App returned to foreground and $totalTime")

        Handler(Looper.getMainLooper()).post {
            val currentTop = MyApplication.getInstance().currentActivity
            if (this != currentTop) {
                println("${this::class.java.simpleName}: is not the top ($currentTop)")
                return@post
            }

//            if (totalTime >= 10 && isHomeActivity) {
//                println("${this::class.java.simpleName}: launching WelcomeActivity")
//                startActivity(Intent(this, WelcomeActivity::class.java))
//            }
        }
    }

    override fun onAppWentToBackground() {
        Log.d(TAG, "${this::class.java.simpleName}: App went to background")
        now = System.currentTimeMillis()
    }

    companion object {
        private var now = 0L
        var isHomeActivity = false
        private var TAG = BaseActivity::class.java.simpleName
        var lastKnownUiMode = Configuration.UI_MODE_NIGHT_NO // default light mode
    }
}