package components.product

import interfaces.UICase
import pages.PageFactory
import pages.Pages

class ProductCase(private val pageFactory: PageFactory) : UICase {
    override val action: String = "Product actions"

    override fun run() {
        pageFactory.create(Pages.PRODUCT)
    }
}