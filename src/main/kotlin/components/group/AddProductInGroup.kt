package components.group

import controller.GroupController
import controller.ProductController
import interfaces.UICase
import model.Group
import model.Product
import service.TableRenderer
import util.InputUtil

class AddProductInGroup(
    private val groupController: GroupController,
    private val productController: ProductController
) : UICase {
    override val action: String = "Add product in group"

    override fun render() {
        groupController.addProduct(getGroup(), getProduct())
    }

    private fun getGroup(): Group {
        val groups = groupController.getAllGroups()
        val table = TableRenderer()
        table.addColumn("#", 10)
        table.addColumn("NAME", 30)
        table.addColumn("PRODUCTS", 10)
        groups.forEachIndexed { i, it ->
            table.addContent(arrayOf(i, it.name, it.products.size))
        }
        table.render()
        return groups[getIndex(groups.size)]
    }

    private fun getProduct(): Product {
        val products = productController.getAllProduct()
        val table = TableRenderer()
        table.addColumn("#", 10)
        table.addColumn("NAME", 30)
        table.addColumn("PRICE", 10)
        products.forEachIndexed { i, it ->
            table.addContent(arrayOf(i, it.name, it.price))
        }
        table.render()
        return products[getIndex(products.size)]
    }

    private fun getIndex(max: Int): Int {
        var index: Int
        do {
            index = InputUtil.getInteger("Chose group", "Illegal value")
        } while (index < 0 || index >= max)
        return index
    }
}