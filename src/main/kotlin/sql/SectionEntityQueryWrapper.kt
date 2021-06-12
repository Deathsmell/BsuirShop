package sql

import interfaces.EntityQueryWrapper
import constant.SqlQueryConstant
import model.Section
import util.DateUtil

class SectionEntityQueryWrapper : EntityQueryWrapper<Section> {
        private val tableName = "section"

        override fun insert(entity: Section): String {
            return "${SqlQueryConstant.INSERT} $tableName ${SqlQueryConstant.VALUES} ($entity);"
        }
}