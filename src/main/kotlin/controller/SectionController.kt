package controller

import model.Product
import model.Section
import service.SectionService
import util.InputUtil

class SectionController(
    private val sectionService: SectionService,
    private val productController: ProductController,
) {

    private val approvals = listOf("Y", "Yes")
    private val disagreements = listOf("N", "No")

    fun createNewSection(): Section {
        val sectionName = getSectionName()
        if (isCreateWithProducts()) {
            val products = mutableListOf<Product>()
            do {
                val createNewProduct = productController.createNewProduct()
                products.add(createNewProduct)
            } while (isCreateNewProduct())
            return sectionService.createSection(sectionName, products)
        }
        return sectionService.createSection(sectionName)
    }

    private fun isCreateNewProduct(): Boolean {
        return InputUtil.getBoolean(
            "Create more product ?",
            approvals,
            disagreements,
        )
    }

    private fun isCreateWithProducts(): Boolean {
        return InputUtil.getBoolean(
            "Create with products ?",
            approvals,
            disagreements,
        )
    }

    private fun getSectionName(): String {
        return InputUtil.getString("Enter section name: ")
    }
}