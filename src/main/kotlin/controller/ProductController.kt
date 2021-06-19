package controller

import model.Product
import service.ProductService
import util.InputUtil
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

    fun getAllProduct(): List<Product> {
        return productService.getAllProducts()
    }

    fun getProductById(): Product? {
        val id = InputUtil.getUUID("Enter product id: ")
        return productService.getProductById(id)
    }

    fun getProductByName(): Product? {
        val name = ProductUtil.getProductName()
        return productService.getProductByName(name)
    }
}