package ru.ke.mainpagearchexample.compose.itemDlegates

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.ke.mainpagearchexample.compose.ItemComposeDelegate
import ru.ke.mainpagearchexample.models.Element
import ru.ke.mainpagearchexample.models.Product

class ProductsBlockComposeDelegate : ItemComposeDelegate<Element.ProductsBlockModel> {

    @Composable
    override fun Content(element: Element.ProductsBlockModel) {
        element.products
            .chunked(2)
            .forEach { products ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                ) {
                    products.forEach { product ->
                        ProductCard(
                            product = product,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
    }

    @Composable
    private fun ProductCard(
        product: Product,
        modifier: Modifier
    ) {
        Box(
            modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
                .aspectRatio(0.7f)
                .border(
                    width = 1.dp,
                    color = Color.Black
                )
        ) {
            Text(
                text = "Product card ${product.id}",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}