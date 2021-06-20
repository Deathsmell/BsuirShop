package sql

import interfaces.EntityQueryWrapper
import constant.SqlQueryConstant
import model.Product
import util.DateUtil
import java.util.*

class ProductEntityQueryWrapper : EntityQueryWrapper<Product> {
    private val tableName = "product"

    override fun getAll(): String {
        return "select * from product"
    }

    override fun getByDate(start: Date, end: Date): String {
        return "select  product.id, product.name, product.price, " +
                "product.description, product.created, product.updated, " +
                "section.id, section.name, section.updated, section.created, " +
                "gr.id, gr.name, gr.created, gr.updated " +
                "from section_products " +
                "left join product on section_products.product_id = product.id " +
                "left join section on section_products.section_id = section.id " +
                "left join groups_productions on product.id = groups_productions.product_id " +
                "left join `group` gr on groups_productions.group_id = gr.id " +
                "where product.created " +
                "between '${DateUtil.getStringDate(start)}' and '${DateUtil.getStringDate(end)}'"
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

    fun getByGroupId(id: UUID): String {
        return "select * from product where id = (select product_id from groups_productions where group_id = '$id')"
    }

    fun getSectionByProductId(id: UUID): String {
        return "select * from section where id = (select section_id from section_products where product_id = '$id')"
    }

    fun getAllWithoutSections(): String {
        return "select * from product where id not in (select product_id from `section_products`)"
    }

    fun getAllBySectionId(id: UUID): String {
        return "select * from product where id = (select product_id from `section_products` where section_id = '$id')"
    }
}