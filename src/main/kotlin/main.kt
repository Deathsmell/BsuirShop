import controller.GroupController
import controller.ProductController
import controller.SectionController
import model.Group
import pages.PageFactory
import pages.Pages
import service.*
import sql.GroupEntityQueryWrapper

fun main() {

    val databaseService = DatabaseService()
    val authService = AuthService(databaseService)

    authService.authentication()

    val productService = ProductService(databaseService)
    val sectionService = SectionService(databaseService)
    val groupService = GroupService(databaseService)
    val productController = ProductController(productService)
    val sectionController = SectionController(sectionService, productController)
    val groupController = GroupController(groupService)
    val pageFactory = PageFactory(sectionController, productController, groupController)

    while (true) {
        pageFactory.create(Pages.MAIN)
    }
}
