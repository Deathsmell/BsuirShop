package model

import util.DateUtil

class Group(val name: String) : Entity() {
    val products = mutableListOf<Product>()

    constructor(name: String, products: Array<Product>) : this(name) {
        this.products.addAll(products)
    }

    override fun toString(): String {
        return "'$id', '$name', '${DateUtil.getDateTime(created)}', '${DateUtil.getDateTime(updated)}'"
    }
}