package components.group

import interfaces.UICase
import pages.PageFactory
import pages.Pages

class GroupCase(private val pageFactory: PageFactory) : UICase {
    override val action: String = "Group actions"

    override fun render() {
        pageFactory.create(Pages.GROUP)
    }
}