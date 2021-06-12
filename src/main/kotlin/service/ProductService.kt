package service

import interfaces.EntityQueryWrapper
import model.Group
import model.Product
import sql.ProductEntityQueryWrapper
import util.InputUtil
import java.util.*

class ProductService(
    private val databaseService: DatabaseService
){
    private val queryWrapper: EntityQueryWrapper<Product> = ProductEntityQueryWrapper()

    fun createProduct(name: String, price: Float): Product {
        val product = Product(name, price)
        val query = queryWrapper.insert(product)
        databaseService.executeUpdate(query)
        return product
    }

    fun createProduct(name: String, price: Float, description: String): Product {
        val product = Product(name, price, description)
        val query = queryWrapper.insert(product)
        databaseService.executeUpdate(query)
        return product
    }

    fun addGroup(product: Product, group: Group): Product {
        if (!isContainGroup(product, group)) {
            product.groups.add(group)
        }
        updateDate(product)
        return product
    }

    fun addGroups(product: Product, groups: Iterable<Group>): Product {
        groups.forEach {
            if (!isContainGroup(product, it)) {
                product.groups.addAll(groups)
            }
        }
        updateDate(product)
        return product
    }

    fun removeGroup(product: Product, group: Group): Product {
        if (isContainGroup(product, group)) {
            product.groups.remove(group)
        }
        updateDate(product)
        return product
    }

    fun removeGroups(product: Product, groups: Iterable<Group>): Product {
        groups.forEach {
            if (isContainGroup(product, it)) {
                product.groups.removeAll(groups)
            }
        }
        updateDate(product)
        return product
    }

    private fun updateDate(product: Product) {
        product.updated = Date()
    }

    private fun isContainGroup(product: Product, group: Group): Boolean {
        return product.groups.contains(group)
    }

    private fun getProductName(): String {
        return InputUtil.getString("Enter name: ")
    }

    private fun getProductPrice(): Float {
        return InputUtil.getFloat(
            "Enter price: ",
            "Illegal price value. You need to enter number value. Please try again!"
        )
    }
}