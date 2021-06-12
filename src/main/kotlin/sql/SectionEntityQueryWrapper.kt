package sql

import `interface`.EntityQueryWrapper
import constant.SqlQueryConstant
import model.Section
import util.DateUtil

class SectionEntityQueryWrapper : EntityQueryWrapper<Section> {
        private val tableName = "section"

        override fun insert(entity: Section): String {
            return "${SqlQueryConstant.INSERT} $tableName VALUES (" +
                    "'${entity.id}', " +
                    "'${entity.name}', " +
                    "'${DateUtil.getDateTime(entity.created)}', " +
                    "'${DateUtil.getDateTime(entity.updated)}'" +
                    ");"
        }
}