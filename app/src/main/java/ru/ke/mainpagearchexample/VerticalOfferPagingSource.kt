package ru.ke.mainpagearchexample

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.ke.mainpagearchexample.data.Gateway
import ru.ke.mainpagearchexample.models.Element

internal class VerticalOfferPagingSource(
    private val offerId: Long,
    private val gateway: Gateway,
) : PagingSource<Int, Element.VerticalOfferElement>() {

    private var offsetSize: Int = 0

    override fun getRefreshKey(state: PagingState<Int, Element.VerticalOfferElement>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(VERTICAL_OFFER_PAGE_SIZE)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(VERTICAL_OFFER_PAGE_SIZE)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Element.VerticalOfferElement> {
        val offset = params.key ?: 0
        return try {
            val items: List<Element.VerticalOfferElement> = gateway.verticalOfferElements(offerId, offset)
            offsetSize = items.size
            val nextKey = if (items.isNotEmpty()) offset + offsetSize else null
            val prevKey = params.key?.let { it - offsetSize }

            LoadResult.Page(items, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
