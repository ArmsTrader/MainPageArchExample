package ru.ke.mainpagearchexample

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.ke.mainpagearchexample.data.Gateway
import ru.ke.mainpagearchexample.models.Element

internal class VerticalOfferPagingSource(
    private val offerId: Long,
    private val gateway: Gateway,
) : PagingSource<Int, Element.VerticalOfferElement>() {

    private var offsetSize: Int = 2

    override fun getRefreshKey(state: PagingState<Int, Element.VerticalOfferElement>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(VERTICAL_OFFER_PAGE_SIZE)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(VERTICAL_OFFER_PAGE_SIZE)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Element.VerticalOfferElement> {
        val offset = params.key ?: 0
        try {
            val prevKey = params.key
                ?.let { it - offsetSize }
                ?.takeIf { it >= 0 }

            val items: List<Element.VerticalOfferElement>? =
                gateway.verticalOfferElements(offerId, offset / offsetSize)

            if (items.isNullOrEmpty()) {
                return LoadResult.Page(emptyList(), prevKey, null)
            }
            val nextKey = if (items.isNotEmpty()) offset + offsetSize else null

            return LoadResult.Page(items, prevKey, nextKey)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}
