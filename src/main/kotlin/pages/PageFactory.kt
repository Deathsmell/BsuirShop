package pages

import components.group.AddProductInGroup
import components.group.CreateGroupCase
import components.group.GetGroup
import components.group.GroupCase
import components.group.get.GetAllGroupCase
import components.product.CreateProductCase
import components.product.GetProduct
import components.product.ProductCase
import components.product.get.*
import components.section.AddProductInSection
import components.section.CreateSectionCase
import components.section.GetSection
import components.section.SectionCase
import components.section.get.GetAllSectionCase
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
    private val addProductInSection = AddProductInSection(sectionController, productController)
    private val getSectionByName = GetSectionByName(sectionController)
    private val getSectionById = GetSectionById(sectionController)
    private val getAllSectionCase = GetAllSectionCase(sectionController, this)

    private val productCase = ProductCase(this)
    private val createProductCase = CreateProductCase(productController)
    private val getProductCase = GetProduct(this)
    private val getAllProductCase = GetAllProductCase(productController)
    private val getProductById = GetProductById(productController)
    private val getProductByName = GetProductByName(productController)
    private val getProductsBySectionId = GetProductsBySectionId(sectionController)
    private val getProductsByGroupId = GetProductsByGroupId(groupController)
    private val getProductByDate = GetProductByDate(productController)

    private val groupCase = GroupCase(this)
    private val getGroupCase = GetGroup(this)
    private val createGroupCase = CreateGroupCase(groupController)
    private val getAllGroups = GetAllGroupCase(groupController)
    private val addProductInGroup = AddProductInGroup(groupController, productController)

    fun create(page: Pages): UIContainer {
        return UIContainer(
            when (page) {
                Pages.MAIN -> getMainPage()
                Pages.SECTION -> getSectionPage()
                Pages.GROUP -> getGroupPage()
                Pages.PRODUCT -> getProductPage()
                Pages.GET_SECTION -> getSectionGetPage()
                Pages.GET_PRODUCT -> getProductGetPage()
                Pages.GET_GROUPS -> getGroupGetPage()
            }
        )
    }

    private fun getGroupGetPage() = listOf(
        getAllGroups,
    )

    private fun getProductGetPage() = listOf(
        getAllProductCase,
        getProductByName,
        getProductById,
        getProductsBySectionId,
        getProductsByGroupId,
        getProductByDate,
    )

    private fun getMainPage() = listOf(
        sectionCase,
        productCase,
        groupCase
    )

    private fun getSectionPage() = listOf(
        createSectionCase,
        getSectionCase,
        addProductInSection,
    )

    private fun getProductPage() = listOf(
        createProductCase,
        getProductCase,
        addProductInSection,
        addProductInGroup
    )

    private fun getGroupPage() = listOf(
        createGroupCase,
        getGroupCase,
        addProductInGroup
    )

    private fun getSectionGetPage() = listOf(
        getAllSectionCase,
        getSectionByName,
        getSectionById,
    )
}