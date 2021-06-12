package sql

import interfaces.EntityQueryWrapper
import constant.SqlQueryConstant
import model.Product

class ProductEntityQueryWrapper : EntityQueryWrapper<Product> {
    private val tableName = "product"

    override fun insert(entity: Product): String {
        return "${SqlQueryConstant.INSERT} $tableName ${SqlQueryConstant.VALUES} ($entity);"
    }
}