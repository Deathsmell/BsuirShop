package sql

import constant.SqlQueryConstant
import interfaces.EntityQueryWrapper
import model.Group
import java.util.*

class GroupEntityQueryWrapper : EntityQueryWrapper<Group> {
    private val tableName = "`group`"

    override fun insert(entity: Group): String {
        return "select * from `group` values ($entity);"
    }

    override fun getById(id: UUID): String {
        return "select * from `group` where id = '$id'"
    }

    override fun getByName(name: String): String {
        return "select * from `group` where name = '$name'"
    }
}