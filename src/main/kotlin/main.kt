import controller.ProductController
import controller.SectionController
import pages.PageFactory
import pages.Pages
import service.*

fun main() {

    val databaseService = DatabaseService()
    val authService = AuthService(databaseService)

    authService.authentication()

    val productService = ProductService(databaseService)
    val sectionService = SectionService(databaseService)
    val productController = ProductController(productService)
    val sectionController = SectionController(sectionService, productController)
    val pageFactory = PageFactory(sectionController, productController)

    while (true) {
        pageFactory.create(Pages.MAIN)
    }
}
