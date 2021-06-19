package service

import model.Product
import model.Section
import repository.SectionRepository
import java.util.*

class SectionService(
    private val sectionRepository: SectionRepository,
) {
    fun createSection(name: String): Section {
        val section = Section(name)
        sectionRepository.createSection(section)
        return section
    }

    fun createSection(name: String, products: MutableCollection<Product>): Section {
        val section = Section(name, products)
        sectionRepository.createSection(section)
        return section
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

    fun addProduct(section: Section, product: Product): Boolean {
        return if (product.section?.id == section.id) {
            false
        } else {
            sectionRepository.addProduct(section.id, product.id)
            true
        }
    }

    fun getAllSectionsWithProducts(): List<Section> {
        return sectionRepository.getAllSectionsWithProducts()
    }
}