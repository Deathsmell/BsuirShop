package service

import model.Product
import repository.ProductRepository
import java.util.*

class ProductService(
    private val productRepository: ProductRepository
) {
    fun createProduct(name: String, price: Float): Product {
        return productRepository.createProduct(name, price)
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

    fun getAllProductsBySectionId(id: UUID): List<Product> {
        return productRepository.getAllProductsBySectionId(id)
    }

    fun getAllProductsByDate(start: Date, end: Date): List<Product> {
        return productRepository.getAllProductByDate(start, end)
    }
}