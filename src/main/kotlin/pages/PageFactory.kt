package pages

import components.AddSectionCase
import components.CreateProductCase
import controller.ProductController
import controller.SectionController

class PageFactory(
    sectionController: SectionController,
    productController: ProductController,
) {

    private val addSectionCase: AddSectionCase = AddSectionCase(sectionController)
    private val createProductCase = CreateProductCase(productController)

    fun create(page: Pages): UIContainer {
        if (page == Pages.MAIN) {
            return UIContainer(
                listOf(
                    addSectionCase,
                    createProductCase
                )
            )
        }
        return UIContainer(
            listOf(
                addSectionCase,
                createProductCase
            )
        )
    }
}