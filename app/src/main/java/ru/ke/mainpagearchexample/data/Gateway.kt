package ru.ke.mainpagearchexample.data

import ru.ke.mainpagearchexample.models.Element
import ru.ke.mainpagearchexample.models.Product

class Gateway {

    fun mainPageElements(): List<Element.MainPageElement> =
        listOf(
            bannerCarousel(),
            popularCategories(),
            verticalOffers(),
        )

    fun verticalOfferElements(offerId: Long, page: Int): List<Element.VerticalOfferElement> =
        listOf(
            inlineBanner()
        )

    private fun bannerCarousel() =
        Element.BannersBlockModel(
            listOf(
                Element.BannersBlockModel.Banner(
                    imageUrl = "",
                    deeplink = "",
                ),
                Element.BannersBlockModel.Banner(
                    imageUrl = "",
                    deeplink = "",
                ),
                Element.BannersBlockModel.Banner(
                    imageUrl = "",
                    deeplink = "",
                ),
            )
        )

    private fun popularCategories() =
        Element.PopularCategoriesBlock(
            title = "Популярные категории",
            deeplink = "magnit://market/categories",
            categories = listOf(
                Element.PopularCategoriesBlock.Category(
                    title = "Электроника",
                    image = "",
                    deeplink = "magnit://market/categories/1"
                ),
                Element.PopularCategoriesBlock.Category(
                    title = "Ремонт",
                    image = "",
                    deeplink = "magnit://market/categories/1"
                ),
                Element.PopularCategoriesBlock.Category(
                    title = "Бытовая техника",
                    image = "",
                    deeplink = "magnit://market/categories/1"
                ),
                Element.PopularCategoriesBlock.Category(
                    title = "Детские товары",
                    image = "",
                    deeplink = "magnit://market/categories/1"
                ),
                Element.PopularCategoriesBlock.Category(
                    title = "Новый год",
                    image = "",
                    deeplink = "magnit://market/categories/1"
                ),
                Element.PopularCategoriesBlock.Category(
                    title = "Для дома",
                    image = "",
                    deeplink = "magnit://market/categories/1"
                ),
            )
        )

    private fun verticalOffers() =
        Element.VerticalOffersBlocks(
            offers = listOf(
                Element.VerticalOffersBlocks.VerticalOffer(
                    id = 0,
                    title = "Рекомендуем"
                ),
                Element.VerticalOffersBlocks.VerticalOffer(
                    id = 1,
                    title = "Популярное"
                ),
                Element.VerticalOffersBlocks.VerticalOffer(
                    id = 2,
                    title = "Распродажа"
                ),
            )
        )

    private fun inlineBanner() = Element.InlineBannerModel(
        imageUrl = "",
        deeplink = "",
    )

    private fun product() = Element.ProductBlockModel(
        product = Product(
            id = 0,
            title = randomProductTitle(),
            imageUrl = randomProductImage()
        )
    )

    private fun randomProductTitle(): String = "" // todo
    private fun randomProductImage(): String = "" // todo
}