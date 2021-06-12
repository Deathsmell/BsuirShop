package service

import controller.ProductController
import controller.SectionController
import pages.PageFactory

class PageService(sectionController: SectionController, productController: ProductController) {
    val pageFactory = PageFactory(sectionController, productController)
}