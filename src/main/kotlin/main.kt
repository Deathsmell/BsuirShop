import controller.ProductController
import controller.SectionController
import service.AuthService
import service.ProductService
import service.SectionService

fun main(args: Array<String>) {
    AuthService().authentication()
    val productService = ProductService()
    val sectionService = SectionService()
    val productController = ProductController(productService)
    val sectionController = SectionController(sectionService, productController)
    sectionController.createNewSection()
}
