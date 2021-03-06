package components.group

import controller.GroupController
import interfaces.UICase

class CreateGroupCase(private val groupController: GroupController) : UICase {
    override val action: String = "Create group"

    override fun render() {
        groupController.createGroup()
    }
}