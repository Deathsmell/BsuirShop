package service

import model.Group
import model.Product
import model.Section
import repository.GroupRepository
import repository.ProductRepository
import sql.GroupEntityQueryWrapper
import sql.entity.GroupMap
import sql.entity.ProductMap
import java.util.*

class GroupService(
    private val groupRepository: GroupRepository,
    private val productRepository: ProductRepository
) {
    fun createGroup(name: String): Group {
        val group = Group(name)
        groupRepository.insertGroup(group)
        return group
    }

    fun getAllGroup(): List<Group> {
        val result = groupRepository.getAll()
        result.forEach {
            val products = productRepository.getProductByGroupId(it.id)
            it.products.addAll(products)
        }
        return result
    }

    fun addProduct(group: Group, product: Product): Boolean {
        return groupRepository.addProduct(group.id, product.id)
    }
}