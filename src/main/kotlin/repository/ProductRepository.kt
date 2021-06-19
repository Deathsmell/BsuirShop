package repository

import model.Product
import model.Section
import service.DatabaseService
import sql.ProductEntityQueryWrapper
import sql.entity.ProductMap
import sql.entity.SectionMap
import java.sql.ResultSet
import java.util.*

class ProductRepository(private val databaseService: DatabaseService) {
    private val queryWrapper: ProductEntityQueryWrapper = ProductEntityQueryWrapper()

    fun createProduct(name: String, price: Float): Product {
        val product = Product(name, price)
        val query = queryWrapper.insert(product)
        databaseService.executeUpdate(query)
        return product
    }

    fun getAllProducts(): List<Product> {
        val query = queryWrapper.getAll()
        val resultSet = databaseService.executeQuery(query)
        return castProductsByResultSet(resultSet)
    }

    private fun castProductsByResultSet(resultSet: ResultSet?): List<Product> {
        val products = mutableListOf<Product>()
        while (resultSet !== null && resultSet.next()) {
            products.add(castProductByResultSet(resultSet))
        }
        return products
    }

    fun getProductByName(name: String): Product? {
        val query = queryWrapper.getByName(name)
        val resultSet = databaseService.executeQuery(query)
        return getProductByResultSet(resultSet)
    }

    fun getProductById(id: UUID): Product? {
        val query = queryWrapper.getById(id)
        val resultSet = databaseService.executeQuery(query)
        return getProductByResultSet(resultSet)
    }

    fun getProductByGroupId(groupId: UUID): List<Product> {
        val query = queryWrapper.getByGroupId(groupId)
        val resultSet = databaseService.executeQuery(query)
        return castProductsByResultSet(resultSet)
    }

    private fun getProductByResultSet(resultSet: ResultSet?): Product? {
        return if (resultSet !== null && resultSet.next()) {
            castProductByResultSet(resultSet)
        } else {
            null
        }
    }

    private fun castProductByResultSet(resultSet: ResultSet): Product {
        val id = Product.getIdByResultSet(resultSet)
        return Product.castProductByResultSet(resultSet, getSectionByProductId(id))
    }

    private fun getSectionByProductId(id: UUID): Section? {
        val query = queryWrapper.getSectionByProductId(id)
        val resultSet = databaseService.executeQuery(query)
        return if (resultSet !== null && resultSet.next()) {
            Section.castSectionByResultSet(resultSet, listOf())
        } else {
            null
        }
    }

    fun getAllProductsWithoutSections(): List<Product> {
        val query = queryWrapper.getAllWithoutSections()
        val resultSet = databaseService.executeQuery(query)
        return castProductsByResultSet(resultSet)
    }
}