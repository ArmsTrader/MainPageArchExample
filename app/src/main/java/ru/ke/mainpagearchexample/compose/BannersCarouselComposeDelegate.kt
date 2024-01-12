@file:OptIn(ExperimentalFoundationApi::class)

package ru.ke.mainpagearchexample.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.ke.mainpagearchexample.Navigation
import ru.ke.mainpagearchexample.models.Element

class BannersCarouselComposeDelegate(
    private val navigation: Navigation
) : ItemComposeDelegate<Element.BannersBlockModel> {

    @Composable
    override fun Content(element: Element.BannersBlockModel) {
        Banners(
            items = element.banners,
            itemClicked = { navigation.navigateToDeeplink(it.deeplink) }
        )
    }


    @Composable
    fun Banners(
        items: List<Element.BannersBlockModel.Banner>,
        itemClicked: (Element.BannersBlockModel.Banner) -> Unit,
    ) {
        val pagerState = rememberPagerState(initialPage = 0) { items.size }

        fun currentItem(): Element.BannersBlockModel.Banner =
            items[pagerState.settledPage % items.size]

        // must be pager with infinite scroll. see ProductPhotosGallery
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.8f)
                .clickable {
                    itemClicked(currentItem())
                },
        ) {
            val item = currentItem()
            AsyncImage(
                model = item.deeplink,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}