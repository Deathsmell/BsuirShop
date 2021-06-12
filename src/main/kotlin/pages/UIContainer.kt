package pages

import interfaces.UICase
import util.InputUtil

class UIContainer(private val actions: List<UICase>) {

    init {
        renderActions()
        var variant: Int
        do {
            variant = InputUtil.getInteger("Chose action: ", "Variant must be integer!")
            val isValidVariant = variant >= 0 && variant < actions.size
            if (!isValidVariant) {
                println("Variant value must be biggest than 0 and lowest than ${actions.size}")
            }
        } while (!isValidVariant)
        actions[variant].run()
    }

    private fun renderActions() {
        actions.forEachIndexed {
            index, uiCase -> println("($index) ${uiCase.action}")
        }
    }
}