package components.section

import controller.ProductController
import controller.SectionController
import interfaces.UICase
import model.Product
import model.Section
import service.TableRenderer
import util.InputUtil

class AddProductInSection(
    private val sectionController: SectionController,
    private val productController: ProductController,
) : UICase {
    override val action: String = "Add product in section"

    override fun render() {
        do {
            var isTryAgain = false
            val section = getSection()
            val product = getProduct()
            if (section == null || product == null) {
                println("All product have section")
                break
            }
            val isSuccess = sectionController.addProduct(section, product)
            if (!isSuccess) {
                println("This section has this product")
                isTryAgain = InputUtil.getBoolean(
                    "You want to try again ?",
                    listOf("y", "Y"),
                    listOf("n", "N"),
                )
            }
        } while (!isSuccess && isTryAgain)
    }

    private fun getSection(): Section? {
        val sections = sectionController.getAllSections()
        if (sections.isEmpty()) {
            return null
        }
        val sectionsTable = TableRenderer()
        sectionsTable.addColumn("#", 6)
        sectionsTable.addColumn("NAME", 30)
        sectionsTable.addColumn("PRODUCTS COUNT", 20)
        sections.forEachIndexed { i, it ->
            sectionsTable.addContent(arrayOf(i, it.name, it.products.size))
        }
        sectionsTable.render()
        val index = getIndex(sections.size)
        return sections[index]
    }

    private fun getProduct(): Product? {
        val products = productController.getProductsWithoutSections()
        if (products.isEmpty()) {
            return null
        }
        val productTable = TableRenderer()
        productTable.addColumn("#", 6)
        productTable.addColumn("NAME", 30)
        productTable.addColumn("PRICE", 20)
        products.forEachIndexed { i, it ->
            productTable.addContent(arrayOf(i, it.name, it.price))
        }
        productTable.render()
        val index = getIndex(products.size)
        return products[index]
    }

    private fun getIndex(max: Int): Int {
        var index: Int
        do {
            index = InputUtil.getInteger(
                "Choose section where you want to add product: ",
                "Invalid value. Please select another number. Try again"
            )
        } while (index < 0 || index >= max)
        return index
    }
}