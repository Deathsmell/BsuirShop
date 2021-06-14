package components.section.get

import controller.SectionController
import interfaces.UICase
import service.Table

class GetSectionByName(private val sectionController: SectionController) : UICase {
    override val action: String = "Get section by name"

    override fun render() {
        val sections = sectionController.getSectionByName()
        val table = Table()
        table.addColumn("ID", 38)
        table.addColumn("NAME", 30)
        table.addColumn("PRODUCTS COUNT", 16)
        sections.forEach {
            table.addContent(arrayOf(it.id, it.name, it.products.size))
        }
        table.render()
    }
}