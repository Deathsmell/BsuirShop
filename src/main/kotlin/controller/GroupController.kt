package controller

import model.Group
import model.Product
import service.GroupService
import util.InputUtil

class GroupController(
    private val groupService: GroupService
) {

    fun createGroup() {
        val groupName = getGroupName()
        groupService.createGroup(groupName)
    }

    fun getAllGroups(): List<Group> {
        return groupService.getAllGroup()
    }

    fun addProduct(group: Group, product: Product): Boolean {
        return groupService.addProduct(group, product)
    }

    fun getAllGroupWithProducts(): List<Group> {
        return groupService.getAllGroupWithProducts()
    }

    private fun getGroupName(): String {
        var name: String
        do {
            name = InputUtil.getString("Enter section name: ")
            val containsWhiteSpace = name.contains(Regex("\\s+"))
            if (containsWhiteSpace) {
                println("Group name does not have whitespaces. Please try again!")
            }
        } while (containsWhiteSpace)
        return name
    }
}