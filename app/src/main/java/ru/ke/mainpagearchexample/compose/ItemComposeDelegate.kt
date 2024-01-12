package ru.ke.mainpagearchexample.compose

import androidx.compose.runtime.Composable
import ru.ke.mainpagearchexample.models.Element

interface ItemComposeDelegate<E : Element> {

    @Composable
    fun Content(element: E)

}

