package service

import interfaces.EntityQueryWrapper
import model.Product
import model.Section
import repository.SectionRepository
import sql.SectionEntityQueryWrapper
import sql.entity.SectionMap
import java.util.*

class SectionService(
    private val sectionRepository: SectionRepository,
) {
    private val queryWrapper: EntityQueryWrapper<Section> = SectionEntityQueryWrapper()

    fun createSection(name: String): Section {
        val section = Section(name)
        sectionRepository.createSection(section)
        return section
    }

    fun createSection(name: String, product: Product): Section {
        val section = Section(name, mutableListOf(product))
        sectionRepository.createSection(section)
        return section
    }

    fun createSection(name: String, products: MutableCollection<Product>): Section {
        val section = Section(name, products)
        sectionRepository.createSection(section)
        return section
    }

    private fun updateDate(section: Section) {
        section.updated = Date()
    }

    fun getAllSections(): List<Section> {
        return sectionRepository.getAllSections()
    }

    fun getSectionById(id: UUID): Section? {
        return sectionRepository.getSectionById(id)
    }

    fun getSectionByName(sectionName: String): List<Section> {
        return sectionRepository.getSectionByName(sectionName)
    }
}