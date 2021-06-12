package service

import model.Product
import model.Section
import java.util.*

class SectionService {

    fun createSection(name: String): Section {
        return Section(name)
    }

    fun createSection(name: String, product: Product): Section {
        return Section(name, listOf(product))
    }

    fun createSection(name: String, products: Iterable<Product>): Section {
        return Section(name, products)
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