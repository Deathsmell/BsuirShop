package model

import java.util.*

class Section(name: String) {
    val products = mutableListOf<Product>()
    val created = Date()
    var updated = Date()

    constructor(name: String, products: Iterable<Product>): this(name) {
        this.products.addAll(products)
        products.forEach {
            it.section = this
        }
    }
}