package components.group.get

import controller.GroupController
import interfaces.UICase
import service.TableRenderer

class GetAllGroupCase(private val groupController: GroupController): UICase {
    override val action: String = "Get all groups"

    override fun render() {
        val groups = groupController.getAllGroups()
        val table = TableRenderer()
        table.addColumn("ID", 36)
        table.addColumn("NAME", 30)
        table.addColumn("PRODUCTS", 10)
        groups.forEach {
            table.addContent(arrayOf(it.id, it.name, it.products.size))
        }
        table.render()
    }
}