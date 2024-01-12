package ru.ke.mainpagearchexample.compose.itemDlegates

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.ke.mainpagearchexample.compose.ItemComposeDelegate
import ru.ke.mainpagearchexample.models.Element

class PopularCategoriesComposeDelegate : ItemComposeDelegate<Element.PopularCategoriesBlock> {
    @Composable
    override fun Content(element: Element.PopularCategoriesBlock) {
        LazyRow(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .aspectRatio(4f)
        ) {
            items(element.categories) { item ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .border(
                            width = 1.dp,
                            color = Color.Black
                        )
                )
                {
                    Text(
                        text = item.title,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(4.dp)
                    )
                }
            }
        }
    }

}