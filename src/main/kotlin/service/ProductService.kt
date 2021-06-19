package service

import interfaces.EntityQueryWrapper
import model.Group
import model.Product
import repository.ProductRepository
import sql.ProductEntityQueryWrapper
import util.InputUtil
import java.util.*

class ProductService(
    private val productRepository: ProductRepository
){
    fun createProduct(name: String, price: Float, description: String? = ""): Product {
        return productRepository.createProduct(name, price, description)
    }


    fun getAllProducts(): List<Product> {
        return productRepository.getAllProducts()
    }

    fun getProductByName(name: String): Product? {
        return productRepository.getProductByName(name)
    }

    fun getProductById(id: UUID): Product? {
        return productRepository.getProductById(id)
    }

    fun getAllProductsWithoutSections(): List<Product> {
        return productRepository.getAllProductsWithoutSections()
    }
}