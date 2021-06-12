package pages

import components.CreateGroupCase
import components.CreateSectionCase
import components.CreateProductCase
import controller.GroupController
import controller.ProductController
import controller.SectionController

class PageFactory(
    sectionController: SectionController,
    productController: ProductController,
    groupController: GroupController,
) {

    private val createSectionCase = CreateSectionCase(sectionController)
    private val createProductCase = CreateProductCase(productController)
    private val createGroupCase = CreateGroupCase(groupController)

    fun create(page: Pages): UIContainer {
        return UIContainer(
            listOf(
                createSectionCase,
                createProductCase,
                createGroupCase,
            )
        )
    }
}