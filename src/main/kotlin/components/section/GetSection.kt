package components.section

import interfaces.UICase
import pages.PageFactory
import pages.Pages

class GetSection(private val pageFactory: PageFactory) : UICase {
    override val action: String = "Get section(s?)"

    override fun render() {
        pageFactory.create(Pages.GET_SECTION)
    }
}