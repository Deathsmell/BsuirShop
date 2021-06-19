package components.product

import interfaces.UICase
import pages.PageFactory
import pages.Pages

class GetProduct(private val pageFactory: PageFactory): UICase {
    override val action: String = "Get product(s?)"

    override fun render() {
        pageFactory.create(Pages.GET_PRODUCT)
    }
}