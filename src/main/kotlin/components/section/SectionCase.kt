package components.section

import interfaces.UICase
import pages.PageFactory
import pages.Pages

class SectionCase(private val pageFactory: PageFactory) : UICase {
    override val action: String = "Sections actions"

    override fun render() {
        pageFactory.create(Pages.SECTION)
    }
}