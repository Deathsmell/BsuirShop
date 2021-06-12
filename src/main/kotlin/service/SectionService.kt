package service

import `interface`.EntityQueryWrapper
import model.Product
import model.Section
import sql.SectionEntityQueryWrapper
import java.util.*

class SectionService(
    private val databaseService: DatabaseService,
){
    private val queryWrapper: EntityQueryWrapper<Section> = SectionEntityQueryWrapper()

    fun createSection(name: String): Section {
        val section = Section(name)
        val query = queryWrapper.insert(section)
        databaseService.executeUpdate(query)
        return section
    }

    fun createSection(name: String, product: Product): Section {
        val section = Section(name, listOf(product))
        val query = queryWrapper.insert(section)
        databaseService.executeUpdate(query)
        return section
    }

    fun createSection(name: String, products: Iterable<Product>): Section {
        val section = Section(name, products)
        val query = queryWrapper.insert(section)
        databaseService.executeUpdate(query)
        return section
    }

    fun addProduct(section: Section, product: Product): Section {
        section.products.add(product)
        updateDate(section)
        return section
    }

    fun addProducts(section: Section, products: Iterable<Product>): Section {
        section.products.addAll(products)
        updateDate(section)
        return section
    }

    private fun updateDate(section: Section) {
        section.updated = Date()
    }
}