package components.group

import interfaces.UICase
import pages.PageFactory
import pages.Pages

class GetGroup(private val pageFactory: PageFactory): UICase {
    override val action: String = "Get groups"

    override fun render() {
        pageFactory.create(Pages.GET_GROUPS)
    }
}