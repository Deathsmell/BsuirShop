import controller.ProductController
import controller.SectionController
import service.AuthService
import service.DatabaseService
import service.ProductService
import service.SectionService

fun main(args: Array<String>) {
    val databaseService = DatabaseService()
    AuthService(databaseService).authentication()
    val productService = ProductService(databaseService)
    val sectionService = SectionService(databaseService)
    val productController = ProductController(productService)
    val sectionController = SectionController(sectionService, productController)
    sectionController.createNewSection()
}
