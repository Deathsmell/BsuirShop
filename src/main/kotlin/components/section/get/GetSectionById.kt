package components.section.get

import controller.SectionController
import interfaces.UICase
import service.TableRenderer

class GetSectionById(private val sectionController: SectionController) : UICase {
    override val action: String = "Get section by id"

    override fun render() {
        val section = sectionController.getSectionById()
        val table = TableRenderer()
        table.addColumn("ID", 38)
        table.addColumn("NAME", 30)
        table.addColumn("PRODUCTS COUNT", 16)
        if (section != null) {
            table.addContent(arrayOf(section.id, section.name, section.products.size))
        }
        table.render()
    }
}