import controller.GroupController
import controller.ProductController
import controller.SectionController
import pages.PageFactory
import pages.Pages
import repository.SectionRepository
import service.*

fun main() {

    val databaseService = DatabaseService()
    val authService = AuthService(databaseService)

    authService.authentication()

    val sectionRepository = SectionRepository(databaseService)
    val productService = ProductService(databaseService)
    val sectionService = SectionService(sectionRepository)
    val groupService = GroupService(databaseService)
    val productController = ProductController(productService)
    val sectionController = SectionController(sectionService, productService)
    val groupController = GroupController(groupService)
    val pageFactory = PageFactory(sectionController, productController, groupController)

    while (true) {
        pageFactory.create(Pages.MAIN)
    }
}
