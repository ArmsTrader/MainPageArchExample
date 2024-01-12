@file:OptIn(ExperimentalFoundationApi::class)

package ru.ke.mainpagearchexample.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import ru.ke.mainpagearchexample.MainPageViewModel
import ru.ke.mainpagearchexample.Navigation
import ru.ke.mainpagearchexample.models.Element

// TODO must be in DI
// ------------ //
val navigation = Navigation()
val itemsDelegates: Map<Class<out Element>,  ItemComposeDelegate<out Element>> = mapOf(
    Element.BannersBlockModel::class.java to BannersCarouselComposeDelegate(navigation)
)
// ------------ //


@Composable
internal fun MainPageScreen(
    state: MainPageViewModel.MainPageState,
) {
    if (state is MainPageViewModel.MainPageState.Data) {
        Content(
            topItems = state.topViews,
            verticalOffers = state.verticalOffers?.offers ?: emptyList(),
            verticalOfferElements = state.lazyVerticalOffers,
        )
    }
}

@Composable
private fun Content(
    topItems: List<Element.MainPageElement>,
    verticalOffers: List<Element.VerticalOffersBlocks.VerticalOffer>,
    verticalOfferElements: Map<Long, Flow<PagingData<Element.VerticalOfferElement>>>,
) {
    var selectedVerticalOffer: Element.VerticalOffersBlocks.VerticalOffer? by remember {
        mutableStateOf(verticalOffers.firstOrNull())
    }
    val selectedOfferItems = selectedVerticalOffer?.id
        ?.let { verticalOfferElements[it] }
        ?.collectAsLazyPagingItems()

    LazyColumn {
        item {
            topItems.forEach { item ->
                val delegate = itemsDelegates[item.javaClass]
                    delegate?.Content(element = item)
            }
        }

        VerticalOffersTabs(verticalOffers, selectedVerticalOffer) { selectedVerticalOffer = it }
        if (selectedOfferItems != null) {
            VerticalOffers(selectedOfferItems)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.VerticalOffersTabs(
    tabItems: List<Element.VerticalOffersBlocks.VerticalOffer>,
    selectedVerticalOffer: Element.VerticalOffersBlocks.VerticalOffer?,
    onSelectOffer: (Element.VerticalOffersBlocks.VerticalOffer) -> Unit,
) {
    stickyHeader {
        TabRow(selectedTabIndex = tabItems.indexOf(selectedVerticalOffer)) {
            tabItems.forEachIndexed { index, model ->
                Tab(
                    text = { Text(model.title) },
                    selected = tabItems.indexOf(selectedVerticalOffer) == index,
                    onClick = { onSelectOffer(model) },
                )
            }
        }
    }

}

fun LazyListScope.VerticalOffers(
    items: LazyPagingItems<Element.VerticalOfferElement>,
) {
    items(count = items.itemCount) { index ->

    }
}

