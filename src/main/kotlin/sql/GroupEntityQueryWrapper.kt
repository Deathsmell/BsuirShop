package sql

import constant.SqlQueryConstant
import interfaces.EntityQueryWrapper
import model.Group

class GroupEntityQueryWrapper : EntityQueryWrapper<Group> {
    private val tableName = "`group`"

    override fun insert(entity: Group): String {
        return "${SqlQueryConstant.INSERT} $tableName ${SqlQueryConstant.VALUES} ($entity);"
    }
}