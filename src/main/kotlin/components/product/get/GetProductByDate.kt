package components.product.get

import controller.ProductController
import interfaces.UICase
import service.TableRenderer

class GetProductByDate(private val productController: ProductController) : UICase {
    override val action: String = "Get products by date"

    override fun render() {
        val products = productController.getProductsByDate()
        if (products.isEmpty()) {
            println("Not find any products")
            return
        }
        println(products.size)
        println(products[products.size - 1])
        val table = TableRenderer()
        table.addColumn("ID", 38)
        table.addColumn("NAME", 20)
        table.addColumn("PRICE ", 10)
        table.addColumn("SECTION NAME", 20)
        table.addColumn("GROUPS", 20)
        table.addColumn("CREATED", 10)
        products.forEach {
            table.addContent(
                arrayOf(
                    it.id,
                    it.name,
                    it.price,
                    it.section?.name,
                    it.groups.joinToString(",\n") { group -> group.name },
                    it.created
                )
            )
        }
        table.render()
    }
}