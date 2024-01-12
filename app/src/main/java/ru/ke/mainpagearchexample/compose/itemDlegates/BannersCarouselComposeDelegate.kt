@file:OptIn(ExperimentalFoundationApi::class)

package ru.ke.mainpagearchexample.compose.itemDlegates

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.ke.mainpagearchexample.Navigation
import ru.ke.mainpagearchexample.compose.ItemComposeDelegate
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
        val pageCount = Int.MAX_VALUE
        val pagerState = rememberPagerState(initialPage = pageCount / 2) { pageCount }

        fun item(page: Int): Element.BannersBlockModel.Banner =
            items[page % items.size]

        fun currentItem(): Element.BannersBlockModel.Banner =
            item(pagerState.settledPage)

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.8f)
                .clickable {
                    itemClicked(currentItem())
                },
        ) { page ->
            val item = item(page)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        color = Color.Black
                    )
            )
            {
                Text(
                    text = item.id.toString(),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    }

}