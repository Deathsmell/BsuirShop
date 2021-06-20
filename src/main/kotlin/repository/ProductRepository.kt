package repository

import model.Group
import model.Product
import model.Section
import service.DatabaseService
import sql.ProductEntityQueryWrapper
import sql.entity.GroupMap
import sql.entity.ProductMap
import sql.entity.SectionMap
import java.sql.ResultSet
import java.util.*
import kotlin.math.log

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

    fun getAllProductsBySectionId(id: UUID): List<Product> {
        val query = queryWrapper.getAllBySectionId(id)
        val resultSet = databaseService.executeQuery(query)
        val result = mutableListOf<Product>()
        while (resultSet !== null && resultSet.next()) {
            result.addAll(castProductsByResultSet(resultSet))
        }
        return result
    }

    fun getAllProductByDate(start: Date, end: Date): List<Product> {
        val query = queryWrapper.getByDate(start, end)
        val resultSet = databaseService.executeQuery(query)
        val result = mutableListOf<Product>()
        while (resultSet !== null && resultSet.next()) {
            val group = getGroup(resultSet)
            val section = getSection(resultSet)
            val product = getProduct(resultSet, if (group !== null) listOf(group) else listOf(), section)
            result.add(product)
        }
        return result
    }

    private fun getSection(resultSet: ResultSet): Section? {
        val prefix = "section."
        val id = resultSet.getString("$prefix${SectionMap.ID.label}")
        if (id === null) {
            return null
        }
        return Section.createBuilder()
            .setId(UUID.fromString(id))
            .setName(resultSet.getString("$prefix${SectionMap.NAME.label}"))
            .setProducts(listOf())
            .setCreated(resultSet.getDate("$prefix${SectionMap.CREATED.label}"))
            .setUpdated(resultSet.getDate("$prefix${SectionMap.UPDATED.label}"))
            .build()
    }

    private fun getGroup(resultSet: ResultSet): Group? {
        val prefix = "gr."
        val id = resultSet.getString("$prefix${GroupMap.ID.label}")
        if (id === null) {
            return null
        }
        return Group.createBuilder()
            .setId(UUID.fromString(id))
            .setName(resultSet.getString("$prefix${GroupMap.NAME.label}"))
            .setCreated(resultSet.getDate("$prefix${GroupMap.CREATED.label}"))
            .setProducts(listOf())
            .setUpdated(resultSet.getTime("$prefix${GroupMap.UPDATED.label}"))
            .build()
    }

    private fun getProduct(resultSet: ResultSet, groups: List<Group>, section: Section?): Product {
        val prefix = "product."
        val id = resultSet.getString("$prefix${ProductMap.ID.label}")
        return Product.createBuilder()
            .setId(UUID.fromString(id))
            .setDescription(resultSet.getString("$prefix${ProductMap.DESCRIPTION.label}"))
            .setName(resultSet.getString("$prefix${ProductMap.NAME.label}"))
            .setPrice(resultSet.getFloat("$prefix${ProductMap.PRICE.label}"))
            .setCreated(resultSet.getDate("$prefix${ProductMap.CREATED.label}"))
            .setUpdated(resultSet.getDate("$prefix${ProductMap.UPDATED.label}"))
            .setGroups(groups)
            .setSection(section)
            .build()
    }
}