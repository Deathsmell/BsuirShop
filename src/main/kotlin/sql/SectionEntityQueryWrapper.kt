package sql

import interfaces.EntityQueryWrapper
import constant.SqlQueryConstant
import model.Section
import java.util.*

class SectionEntityQueryWrapper : EntityQueryWrapper<Section> {
    private val sectionTableName = "section"
    private val sectionProductsTableName = "section_products"

    override fun insert(entity: Section): String {
        return "insert into section values ($entity);"
    }

    override fun getAll(): String {
        return "select * from section;"
    }

    override fun getById(id: UUID): String {
        return "select * from section where id = '$id'"
    }

    override fun getByName(name: String): String {
        return "select * from section where name = '$name'"
    }

    fun getProductBySectionId(id: UUID): String {
        return "select * from product where id in (select product_id from section_products where section_id = '$id')"
    }

    fun addProduct(sectionId: UUID, productId: UUID): String {
        return "insert into section_products value ('$sectionId', '$productId')"
    }
}