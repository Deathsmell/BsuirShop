package sql

import interfaces.EntityQueryWrapper
import model.Group
import java.util.*

class GroupEntityQueryWrapper : EntityQueryWrapper<Group> {
    private val group = "`group`"

    override fun insert(entity: Group): String {
        return "insert into $group value ($entity);"
    }

    override fun getByDate(start: Date, end: Date): String {
        TODO("Not yet implemented")
    }

    override fun getById(id: UUID): String {
        return "select * from $group where id = '$id'"
    }

    override fun getByName(name: String): String {
        return "select * from $group where name = '$name'"
    }

    override fun getAll(): String {
        return "select * from $group"
    }

    fun addProduct(groupId: UUID, productId: UUID): String {
        return "insert into groups_productions value ('$groupId', '$productId')"
    }

    fun getProductByGroupId(id: UUID): String {
        return "select * from product where id = (select product_id from groups_productions where group_id = '$id'"
    }

    fun getAllWithProducts(): String {
        return "select * from $group where id in (select group_id from groups_productions)"
    }
}