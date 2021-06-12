package controller

import model.Product
import service.ProductService
import util.InputUtil

class ProductController(val productService: ProductService) {

    fun createNewProduct(): Product {
        val productName = getProductName()
        val productPrice = getProductPrice()
        return productService.createProduct(productName, productPrice)
    }

    private fun getProductName(): String {
        return InputUtil.getString("Enter name: ")
    }

    private fun getProductPrice(): Float {
        return InputUtil.getFloat(
            "Enter price: ",
            "Illegal price value. You need to enter number value. Please try again!"
        )
    }
}