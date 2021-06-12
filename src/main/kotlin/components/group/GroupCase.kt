package components.group

import interfaces.UICase
import pages.PageFactory
import pages.Pages

class GroupCase(private val pageFactory: PageFactory) : UICase {
    override val action: String = "Group actions"

    override fun run() {
        pageFactory.create(Pages.GROUP)
    }
}