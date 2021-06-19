package sql

import interfaces.EntityQueryWrapper
import constant.SqlQueryConstant
import model.Product
import java.util.*

class ProductEntityQueryWrapper : EntityQueryWrapper<Product> {
    private val tableName = "product"

    override fun getAll(): String {
        return "select * from product"
    }

    override fun insert(entity: Product): String {
        return "${SqlQueryConstant.INSERT} $tableName VALUES ($entity);"
    }

    override fun getById(id: UUID): String {
        return "select * from product where id = '$id'"
    }

    override fun getByName(name: String): String {
        return "select * from product where name = '$name'"
    }

    fun getSectionByProductId(id: UUID): String {
        return "select * from section where id = (select section_id from section_products where product_id = '$id')"
    }

    fun getAllWithoutSections(): String {
        return "select * from product where id not in (select product_id from `section_products`)"
    }
}