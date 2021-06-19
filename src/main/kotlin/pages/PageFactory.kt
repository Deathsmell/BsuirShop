package pages

import components.group.CreateGroupCase
import components.group.GroupCase
import components.section.CreateSectionCase
import components.product.CreateProductCase
import components.product.GetProduct
import components.product.ProductCase
import components.product.get.GetAllProductCase
import components.product.get.GetProductById
import components.product.get.GetProductByName
import components.section.get.GetAllSectionCase
import components.section.GetSection
import components.section.SectionCase
import components.section.get.GetSectionById
import components.section.get.GetSectionByName
import controller.GroupController
import controller.ProductController
import controller.SectionController

class PageFactory(
    sectionController: SectionController,
    productController: ProductController,
    groupController: GroupController,
) {
    private val sectionCase = SectionCase(this)
    private val createSectionCase = CreateSectionCase(sectionController)
    private val getSectionCase = GetSection(this)
    private val getSectionByName = GetSectionByName(sectionController)
    private val getSectionById = GetSectionById(sectionController)
    private val getAllSectionCase = GetAllSectionCase(sectionController, this)

    private val productCase = ProductCase(this)
    private val createProductCase = CreateProductCase(productController)
    private val getProductCase = GetProduct(this)
    private val getAllProductCase = GetAllProductCase(productController)
    private val getProductById = GetProductById(productController)
    private val getProductByName = GetProductByName(productController)

    private val createGroupCase = CreateGroupCase(groupController)

    private val groupCase = GroupCase(this)

    fun create(page: Pages): UIContainer {
        return UIContainer(
            when (page) {
                Pages.MAIN -> getMainPage()
                Pages.SECTION -> getSectionPage()
                Pages.GROUP -> getGroupPage()
                Pages.PRODUCT -> getProductPage()
                Pages.GET_SECTION -> getSectionGetPage()
                Pages.GET_PRODUCT -> getProductGetPage()
            }
        )
    }

    private fun getProductGetPage() = listOf(
        getAllProductCase,
        getProductByName,
        getProductById,
    )

    private fun getMainPage() = listOf(
        sectionCase,
        productCase,
        groupCase
    )

    private fun getSectionPage() = listOf(
        createSectionCase,
        getSectionCase,
    )

    private fun getProductPage() = listOf(
        createProductCase,
        getProductCase,
    )

    private fun getGroupPage() = listOf(
        createGroupCase,
    )

    private fun getSectionGetPage() = listOf(
        getAllSectionCase,
        getSectionByName,
        getSectionById,
    )
}