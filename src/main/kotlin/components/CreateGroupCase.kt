package components

import controller.GroupController
import interfaces.UICase

class CreateGroupCase(private val groupController: GroupController) : UICase {
    override val action: String = "Create group"

    override fun run() {
        groupController.createGroup()
    }
}