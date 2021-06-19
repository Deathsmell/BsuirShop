package components.product.get

import controller.ProductController
import interfaces.UICase
import service.Table

class GetAllProductCase(private val productController: ProductController): UICase {
    override val action: String = "Get all products"

    override fun render() {
        val products = productController.getAllProduct()
        val table = Table()
        table.addColumn("ID", 38)
        table.addColumn("NAME", 30)
        table.addColumn("PRICE", 30)
        table.addColumn("SECTION NAME", 30)
        products.forEach {
            val name = if (it.section !== null) it.section?.name else "None"
            table.addContent(arrayOf(it.id, it.name, it.price, name))
        }
        table.render()
    }
}