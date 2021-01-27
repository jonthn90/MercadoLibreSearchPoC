package dev.jonthn.mercadolibresearch.ui

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.stealthcopter.networktools.Ping
import com.stealthcopter.networktools.ping.PingResult
import com.stealthcopter.networktools.ping.PingStats
import dev.jonthn.mercadolibresearch.R
import dev.jonthn.mercadolibresearch.ui.common.ConnectionLiveData
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    companion object {
        var isInternet: Boolean = true
    }

    private val connectionLiveData: ConnectionLiveData by lazy { ConnectionLiveData(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MercadoLibreSearch)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    override fun onResume() {
        super.onResume()

        connectionLiveData.observe(this, Observer { isConnected ->
            isConnected?.let {
                checkPing(it)
            }
        })
    }

    private fun checkPing(isConnected: Boolean) {
        Ping.onAddress("8.8.8.8").setTimeOutMillis(5000).setTimes(4).setDelayMillis(700)
            .doPing(object : Ping.PingListener {
                override fun onResult(pingResult: PingResult?) {
                    isInternet = isConnected && pingResult!!.isReachable

                    Timber.d("Internet $isInternet")
                }

                override fun onFinished(pingStats: PingStats?) {
                    this@MainActivity.runOnUiThread(Runnable {
                        if (!isInternet)
                            Toast.makeText(this@MainActivity, "Sin Internet", Toast.LENGTH_LONG)
                                .show()
                    })
                }

                override fun onError(e: java.lang.Exception?) {
                    isInternet = false
                }
            })
    }
}