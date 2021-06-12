package service

import interfaces.EntityQueryWrapper
import model.Group
import sql.GroupEntityQueryWrapper

class GroupService(
    private val databaseService: DatabaseService
) {
    private val queryWrapper: EntityQueryWrapper<Group> = GroupEntityQueryWrapper()

    fun createGroup(name: String): Group {
        val group = Group(name)
        val query = queryWrapper.insert(group)
        databaseService.executeUpdate(query)
        return group
    }
}