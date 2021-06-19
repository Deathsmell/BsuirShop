package components.section.get

import controller.SectionController
import interfaces.UICase
import pages.PageFactory
import service.TableRenderer

class GetAllSectionCase(
    private val sectionController: SectionController,
    private val pageFactory: PageFactory
) : UICase {
    override val action: String = "Get all sections"

    override fun render() {
        val sections = sectionController.getAllSections()
        val table = TableRenderer()
        table.addColumn("ID", 38)
        table.addColumn("NAME", 30)
        table.addColumn("PRODUCTS COUNT", 16)
        sections.forEach {
            table.addContent(arrayOf(it.id, it.name, it.products.size))
        }
        table.render()
    }
}