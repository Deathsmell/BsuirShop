package sql

import interfaces.EntityQueryWrapper
import constant.SqlQueryConstant
import model.Product
import java.util.*

class ProductEntityQueryWrapper : EntityQueryWrapper<Product> {
    private val tableName = "product"

    override fun insert(entity: Product): String {
        return "${SqlQueryConstant.INSERT} $tableName VALUES ($entity);"
    }

    override fun getById(id: UUID): String {
        return "select * from product where id = '$id'"
    }

    override fun getByName(name: String): String {
        return "select * from product where name = '$name'"
    }
}