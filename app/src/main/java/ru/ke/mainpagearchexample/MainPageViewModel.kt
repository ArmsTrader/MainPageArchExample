package ru.ke.mainpagearchexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.ke.mainpagearchexample.data.Gateway
import ru.ke.mainpagearchexample.models.Element

internal class MainPageViewModel : ViewModel() {

    // TODO must be in DI
    // ------------ //
    private val gateway: Gateway = Gateway()

    private val pagingSources: MutableMap<Long, VerticalOfferPagingSource> = mutableMapOf()

    private fun getOrCreateVerticalOfferPagingSourceFactory(offerId: Long): VerticalOfferPagingSource {
        if (pagingSources.contains(offerId)) {
            return pagingSources[offerId]!!
        }
        return VerticalOfferPagingSource(offerId, gateway).also { pagingSources[offerId] = it }
    }
    // ------------ //

    val state = MutableStateFlow<MainPageState>(MainPageState.Loading)

    init {
        loadData()
    }


    private fun loadData() {
        state.value = MainPageState.Loading
        viewModelScope.launch {
            runCatching { gateway.mainPageElements() }
                .onFailure {
                    state.value = MainPageState.Error
                }
                .onSuccess { mainPageElements ->

                    val verticalOffers = mainPageElements
                        .filterIsInstance<Element.VerticalOffersBlocks>()
                        .firstOrNull()

                    val topViews = verticalOffers
                        ?.let { mainPageElements - verticalOffers }
                        ?: mainPageElements

                    state.value = MainPageState.Data(
                        topViews = topViews,
                        verticalOffers = verticalOffers,
                        lazyVerticalOffers = verticalOffers?.associateWithItemPagingData()
                            ?: emptyMap()
                    )
                }
        }
    }

    private fun Element.VerticalOffersBlocks.associateWithItemPagingData():
            Map<Long, Flow<PagingData<Element.VerticalOfferElement>>> =
        offers.associate { it.id to createVerticalOfferPagingDataFlow(it.id) }


    private fun createVerticalOfferPagingDataFlow(offerId: Long): Flow<PagingData<Element.VerticalOfferElement>> {
        val source = getOrCreateVerticalOfferPagingSourceFactory(offerId)
        val pagingSourceFactory = { source }
        return Pager(
            PagingConfig(pageSize = VERTICAL_OFFER_PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory,
        ).flow.cachedIn(viewModelScope)
    }


    internal sealed class MainPageState {
        data object Loading : MainPageState()
        data object Error : MainPageState()
        data class Data(
            val topViews: List<Element.MainPageElement>,
            val verticalOffers: Element.VerticalOffersBlocks?,
            val lazyVerticalOffers: Map<Long, Flow<PagingData<Element.VerticalOfferElement>>>,
        ) : MainPageState()
    }

}


