package sql

import `interface`.EntityQueryWrapper
import constant.SqlQueryConstant
import model.Product
import util.DateUtil

class ProductEntityQueryWrapper : EntityQueryWrapper<Product> {
    private val tableName = "product"

    override fun insert(entity: Product): String {
        return "${SqlQueryConstant.INSERT} $tableName VALUES (" +
                "'${entity.id}', " +
                "'${entity.name}', " +
                "'${entity.price}', " +
                "'${entity.description}', " +
                "'${DateUtil.getDateTime(entity.created)}', " +
                "'${DateUtil.getDateTime(entity.updated)}'" +
                ");"
    }
}