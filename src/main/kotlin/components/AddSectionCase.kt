package components

import controller.SectionController
import interfaces.UICase

class AddSectionCase(private val sectionController: SectionController) : UICase {
    override val action: String = "Add selection case"
    override fun run() {
        sectionController.createNewSection()
    }
}