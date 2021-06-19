package components.product.get

import controller.GroupController
import interfaces.UICase
import model.Group
import service.TableRenderer
import util.InputUtil

class GetProductsByGroupId(private val groupController: GroupController) : UICase {
    override val action: String = "Get by group"

    override fun render() {
        val groups = getGroups()
        if (groups === null) {
            println("Not find groups with products")
            return
        }
        val table = TableRenderer()
        table.addColumn("NAME", 30)
        table.addColumn("PRICE", 10)
        groups.products.forEach {
            table.addContent(arrayOf(it.name, it.price))
        }
        table.render()
    }

    private fun getGroups(): Group? {
        val groups = groupController.getAllGroupWithProducts()
        if (groups.isEmpty()) {
            return null
        }
        val table = TableRenderer()
        table.addColumn("#", 6)
        table.addColumn("NAME", 30)
        table.addColumn("PRODUCTS COUNT", 30)
        groups.forEachIndexed {i, it ->
            table.addContent(arrayOf(i, it.name, it.products.size))
        }
        table.render()
        val index = InputUtil.getIndex("Chose group: ", groups.size)
        return groups[index]
    }
}