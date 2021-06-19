package components.product.get

import controller.SectionController
import interfaces.UICase
import model.Section
import service.TableRenderer
import util.InputUtil

class GetProductsBySectionId(
    private val sectionController: SectionController,
) : UICase {
    override val action: String = "Get by section"

    override fun render() {
        val section = getSection()
        if (section === null) {
            println("Section with products not exist")
            return
        }
        val table = TableRenderer()
        table.addColumn("ID", 38)
        table.addColumn("NAME", 30)
        table.addColumn("PRICE", 16)
        table.addColumn("GROUPS", 30)
        section.products.forEach {
            table.addContent(arrayOf(it.id, it.name, it.price, it.groups.joinToString(" , ")))
        }
        table.render()
    }

    private fun getSection(): Section? {
        val sections = sectionController.getAllSectionsWithProducts()
        if (sections.isNotEmpty()) {
            val table = TableRenderer()
            table.addColumn("#", 6)
            table.addColumn("NAME", 30)
            table.addColumn("PRODUCTS COUNT", 30)
            sections.forEachIndexed { i, it ->
                table.addContent(arrayOf(i, it.name, it.products.size))
            }
            table.render()
            val index = InputUtil.getIndex("Chose section ", sections.size)
            return sections[index]
        }
        return null
    }
}