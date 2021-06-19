package repository

import model.Product
import model.Section
import service.DatabaseService
import sql.SectionEntityQueryWrapper
import sql.entity.SectionMap
import java.sql.ResultSet
import java.util.*

class SectionRepository(
    private val databaseService: DatabaseService
) {
    private val queryWrapper: SectionEntityQueryWrapper = SectionEntityQueryWrapper()

    fun createSection(section: Section): Section {
        val query = queryWrapper.insert(section)
        databaseService.executeUpdate(query)
        return section
    }

    fun getAllSections(): List<Section> {
        val query = queryWrapper.getAll()
        val resultSet = databaseService.executeQuery(query)
        return getListResults(resultSet)
    }

    fun getSectionById(id: UUID): Section? {
        val query = queryWrapper.getById(id)
        val resultSet = databaseService.executeQuery(query)
        return if (resultSet !== null) {
            resultSet.next()
            castSectionByResultSet(resultSet)
        } else {
            null
        }
    }

    fun getSectionByName(name: String): List<Section> {
        val query = queryWrapper.getByName(name)
        val resultSet = databaseService.executeQuery(query)
        return getListResults(resultSet)
    }

    private fun getListResults(resultSet: ResultSet?): List<Section> {
        val result = mutableListOf<Section>()
        if (resultSet != null) {
            while (resultSet.next()) {
                val section = castSectionByResultSet(resultSet)
                result.add(section)
            }
        }
        return result.toList()
    }

    private fun castSectionByResultSet(resultSet: ResultSet): Section {
        val id = UUID.fromString(resultSet.getString(SectionMap.ID.label))
        val products = getProductsBySectionId(id)
        println(products.size)
        val section = Section.castSectionByResultSet(resultSet, products)
        products.forEach { it.section = section}
        return section

    }

    private fun getProductsBySectionId(id: UUID): Collection<Product> {
        val query = queryWrapper.getProductBySectionId(id)
        val resultSet = databaseService.executeQuery(query)
        val result = mutableListOf<Product>()
        while (resultSet !== null && resultSet.next()) {
            val product = Product.castProductByResultSet(resultSet, null)
            result.add(product)
        }
        return result
    }

    fun addProduct(sectionId: UUID, productId: UUID) {
        val query = queryWrapper.addProduct(sectionId, productId)
        databaseService.executeUpdate(query)
    }
}