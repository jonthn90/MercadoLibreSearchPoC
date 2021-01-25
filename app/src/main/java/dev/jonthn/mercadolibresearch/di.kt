package dev.jonthn.mercadolibresearch

import android.app.Application
import dev.jonthn.data.repository.ItemsSearchRepository
import dev.jonthn.data.source.RemoteDataSource
import dev.jonthn.mercadolibresearch.data.server.API
import dev.jonthn.mercadolibresearch.data.server.APIDataSource
import dev.jonthn.mercadolibresearch.ui.detail.DetailFragment
import dev.jonthn.mercadolibresearch.ui.detail.DetailViewModel
import dev.jonthn.mercadolibresearch.ui.results.ResultsFragment
import dev.jonthn.mercadolibresearch.ui.results.ResultsViewModel
import dev.jonthn.mercadolibresearch.ui.search.SearchFragment
import dev.jonthn.mercadolibresearch.ui.search.SearchViewModel
import dev.jonthn.usescases.SearchItems
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
    factory<RemoteDataSource> { APIDataSource(get()) }
    single { API() }
}

val dataModule = module {
    factory { ItemsSearchRepository(get()) }
}

private val scopesModule = module {
    scope(named<SearchFragment>()) {
        viewModel { SearchViewModel() }
    }

    scope(named<ResultsFragment>()) {
        viewModel { (textSearch: String) -> ResultsViewModel(textSearch, get()) }
        scoped { SearchItems(get()) }
    }

    scope(named<DetailFragment>()) {
        viewModel { (idItem: String) -> DetailViewModel(idItem) }
    }
}