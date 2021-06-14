package controller

import model.Product
import service.ProductService
import util.ProductUtil

class ProductController(private val productService: ProductService) {

    fun createNewProduct(): Product {
        val productName = ProductUtil.getProductName()
        val productPrice = ProductUtil.getProductPrice()
        return productService.createProduct(productName, productPrice)
    }

    fun createNewProductWithDescription(): Product {
        val productName = ProductUtil.getProductName()
        val productDescription = ProductUtil.getProductDescription()
        val productPrice = ProductUtil.getProductPrice()
        return productService.createProduct(productName, productPrice, productDescription)
    }
}