package pages

import components.group.CreateGroupCase
import components.group.GroupCase
import components.section.CreateSectionCase
import components.product.CreateProductCase
import components.product.ProductCase
import components.section.SectionCase
import controller.GroupController
import controller.ProductController
import controller.SectionController
import interfaces.UICase

class PageFactory(
    sectionController: SectionController,
    productController: ProductController,
    groupController: GroupController,
) {
    private val createSectionCase = CreateSectionCase(sectionController)
    private val createProductCase = CreateProductCase(productController)
    private val createGroupCase = CreateGroupCase(groupController)
    private val sectionCase = SectionCase(this)
    private val groupCase = GroupCase(this)
    private val productCase = ProductCase(this)

    fun create(page: Pages): UIContainer {
        return UIContainer(
            when (page) {
                Pages.MAIN -> getMainPage()
                Pages.SECTION -> getSectionPage()
                Pages.GROUP -> getGroupPage()
                Pages.PRODUCT -> getProductPage()
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
    )

    private fun getProductPage() = listOf(
        createProductCase,
    )

    private fun getGroupPage() = listOf(
        createGroupCase,
    )
}