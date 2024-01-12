package ru.ke.mainpagearchexample.data

import ru.ke.mainpagearchexample.models.Element
import ru.ke.mainpagearchexample.models.Product

class Gateway {

    fun mainPageElements(): List<Element.MainPageElement> =
        listOf(
            bannerCarousel(),
            popularCategories(),
            inlineBanner(),
            verticalOffers(),
        )

    fun verticalOfferElements(
        offerId: Long,
        page: Int
    ): List<Element.VerticalOfferElement>? =
        when (page) {
            0 -> page0()
            1 -> page1()
            else -> null
        }

    private fun page0(): List<Element.VerticalOfferElement> =
        listOf(inlineBanner()) + productBlock()

    private fun page1(): List<Element.VerticalOfferElement> =
        listOf(inlineBanner()) + productBlock()

    private fun productBlock(): List<Element.VerticalOfferElement> =
        listOf(
            Element.ProductsBlockModel(
                products = listOf(
                    randomProduct(),
                    randomProduct(),
                    randomProduct(),
                    randomProduct(),
                    randomProduct(),
                    randomProduct(),
                )
            )
        )

    private fun bannerCarousel() =
        Element.BannersBlockModel(
            listOf(
                Element.BannersBlockModel.Banner(
                    id = 0,
                    imageUrl = "",
                    deeplink = "",
                ),
                Element.BannersBlockModel.Banner(
                    id = 1,
                    imageUrl = "",
                    deeplink = "",
                ),
                Element.BannersBlockModel.Banner(
                    id = 2,
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
                    title = "Новый год"
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
        id = (0..1000L).random(),
        imageUrl = "",
        deeplink = "",
    )

    private fun randomProduct() =
        Product(
            id = (0..1000L).random(),
            title = randomProductTitle(),
            imageUrl = randomProductImage()
        )


    private fun randomProductTitle(): String = "" // todo
    private fun randomProductImage(): String = "" // todo
}