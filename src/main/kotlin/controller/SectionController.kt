package controller

import model.Product
import model.Section
import service.ProductService
import service.SectionService
import util.InputUtil
import util.ProductUtil
import util.SectionUtil
import java.util.*

class SectionController(
    private val sectionService: SectionService,
    private val productService: ProductService,
) {

    private val approvals = listOf("Y", "Yes")
    private val disagreements = listOf("N", "No")

    fun createSection(): Section {
        val sectionName = SectionUtil.getSectionName()
        if (isCreateWithProducts()) {
            val products = mutableListOf<Product>()
            do {
                val productName = ProductUtil.getProductName()
                val productPrice = ProductUtil.getProductPrice()
                val createNewProduct = productService.createProduct(productName, productPrice)
                products.add(createNewProduct)
            } while (isCreateNewProduct())
            return sectionService.createSection(sectionName, products)
        }
        return sectionService.createSection(sectionName)
    }

    fun getAllSections(): List<Section> {
        return sectionService.getAllSections()
    }

    fun getSectionByName(): List<Section> {
        val sectionName = SectionUtil.getSectionName()
        return sectionService.getSectionByName(sectionName)
    }

    fun getSectionById(): Section? {
        val id = SectionUtil.getSectionId()
        return sectionService.getSectionById(id)
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
}