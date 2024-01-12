package ru.ke.mainpagearchexample.models


/**
 * Main page block
 */
interface Element {

    interface MainPageElement : Element
    interface VerticalOfferElement : Element

    class BannersBlockModel(
        val banners: List<Banner>
    ) : MainPageElement {
        class Banner(
            val id: Long,
            val imageUrl: String,
            val deeplink: String,
        )
    }

    class InlineBannerModel(
        val id: Long,
        val imageUrl: String,
        val deeplink: String,
    ) : MainPageElement, VerticalOfferElement

    class PopularCategoriesBlock(
        val title: String,
        val deeplink: String,
        val categories: List<Category>
    ) : MainPageElement, VerticalOfferElement {
        class Category(
            val id: Long = (0..1000L).random(),
            val title: String,
            val image: String,
            val deeplink: String
        )
    }

    class VerticalOffersBlocks(
        val offers: List<VerticalOffer>
    ) : MainPageElement {
        class VerticalOffer(
            val id: Long,
            val title: String
        )
    }

    class ProductsBlockModel(
        val products: List<Product>
    ) : VerticalOfferElement

}

class Product(
    val id: Long,
    val title: String,
    val imageUrl: String,
)