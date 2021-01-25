package dev.jonthn.mercadolibresearch

import android.app.Application
import dev.jonthn.mercadolibresearch.ui.results.ResultsFragment
import dev.jonthn.mercadolibresearch.ui.results.ResultsViewModel
import dev.jonthn.mercadolibresearch.ui.search.SearchFragment
import dev.jonthn.mercadolibresearch.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger(Level.NONE)
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {

}

val dataModule = module {

}

private val scopesModule = module {
    scope(named<SearchFragment>()) {
        viewModel { SearchViewModel() }
    }

    scope(named<ResultsFragment>()) {
        viewModel { (textSearch: String) -> ResultsViewModel(textSearch) }
    }
}