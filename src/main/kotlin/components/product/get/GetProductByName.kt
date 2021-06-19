package components.product.get

import controller.ProductController
import interfaces.UICase
import service.TableRenderer

class GetProductByName(private val productController: ProductController) : UICase {
    override val action: String = "Get product by name"

    override fun render() {
        val product = productController.getProductByName()
        val table = TableRenderer()
        table.addColumn("ID", 38)
        table.addColumn("NAME", 30)
        table.addColumn("PRICE", 16)
        table.addColumn("SECTION NAME", 30)
        if (product != null) {
            val sectionName = if (product.section !== null) product.section?.name else "None"
            table.addContent(arrayOf(product.id, product.name, product.price, sectionName ))
        }
        table.render()
    }
}