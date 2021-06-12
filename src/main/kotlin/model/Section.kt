package model

import util.DateUtil

class Section(val name: String) : Entity() {
    val products = mutableListOf<Product>()

    constructor(name: String, products: Iterable<Product>) : this(name) {
        this.products.addAll(products)
        products.forEach {
            it.section = this
        }
    }

    override fun toString(): String {
        return "'$id', '$name', '${DateUtil.getDateTime(created)}', '${DateUtil.getDateTime(updated)}'"
    }
}