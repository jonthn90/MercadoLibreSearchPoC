package dev.jonthn.mercadolibresearch

import android.app.Application
import timber.log.Timber

class MercadoLibreSearchApp : Application(){

    override fun onCreate() {
        super.onCreate()

        initDI()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}