package pages

import components.group.CreateGroupCase
import components.group.GroupCase
import components.section.CreateSectionCase
import components.product.CreateProductCase
import components.product.ProductCase
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
    private val createSectionCase = CreateSectionCase(sectionController)
    private val getSectionCase = GetSection(this)
    private val getSectionByName = GetSectionByName(sectionController)
    private val getSectionById = GetSectionById(sectionController)
    private val createProductCase = CreateProductCase(productController)
    private val createGroupCase = CreateGroupCase(groupController)
    private val sectionCase = SectionCase(this)
    private val groupCase = GroupCase(this)
    private val productCase = ProductCase(this)
    private val getAllSectionCase = GetAllSectionCase(sectionController, this)

    fun create(page: Pages): UIContainer {
        return UIContainer(
            when (page) {
                Pages.MAIN -> getMainPage()
                Pages.SECTION -> getSectionPage()
                Pages.GROUP -> getGroupPage()
                Pages.PRODUCT -> getProductPage()
                Pages.GET_SECTION -> getSectionGetPage()
            }
        )
    }

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