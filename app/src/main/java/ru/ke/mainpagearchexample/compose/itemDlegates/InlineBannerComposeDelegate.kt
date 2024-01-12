@file:OptIn(ExperimentalFoundationApi::class)

package ru.ke.mainpagearchexample.compose.itemDlegates

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.ke.mainpagearchexample.Navigation
import ru.ke.mainpagearchexample.compose.ItemComposeDelegate
import ru.ke.mainpagearchexample.models.Element

class InlineBannerComposeDelegate(
    private val navigation: Navigation
) : ItemComposeDelegate<Element.InlineBannerModel> {

    @Composable
    override fun Content(element: Element.InlineBannerModel) {
        InlineBanner(
            banner = element,
            itemClicked = { navigation.navigateToDeeplink(element.deeplink) }
        )
    }


    @Composable
    fun InlineBanner(
        banner: Element.InlineBannerModel,
        itemClicked: () -> Unit,
    ) {

        Box(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
                .aspectRatio(2.5f)
                .border(
                    width = 1.dp,
                    color = Color.Black
                )
                .clickable { itemClicked() }
        )
        {
            Text(
                text = "Inline Banner ${banner.id}",
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }

}