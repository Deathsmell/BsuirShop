package components.product

import controller.ProductController
import interfaces.UICase

class CreateProductCase(private val productController: ProductController) : UICase {
    override val action: String = "Create new product"
    override fun run() {
        productController.createNewProduct()
    }
}