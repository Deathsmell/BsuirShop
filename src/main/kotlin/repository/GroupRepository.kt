package repository

import model.Group
import service.DatabaseService
import sql.GroupEntityQueryWrapper
import java.util.*

class GroupRepository(
    private val databaseService: DatabaseService
) {
    private val queryWrapper: GroupEntityQueryWrapper = GroupEntityQueryWrapper()

    fun insertGroup(group: Group) {
        val query = queryWrapper.insert(group)
        databaseService.executeUpdate(query)
    }

    fun getAll(): List<Group> {
        val query = queryWrapper.getAll()
        val resultSet = databaseService.executeQuery(query)
        val result = mutableListOf<Group>()
        while (resultSet !== null && resultSet.next()) {
            result.add(Group.castGroupByResultSet(resultSet, listOf()))
        }
        return result
    }

    fun addProduct(groupId: UUID, productId: UUID): Boolean {
        val query = queryWrapper.addProduct(groupId, productId)
        val result = databaseService.executeUpdate(query)
        return result !== null
    }
}