package model

import java.util.*

class Section(val name: String) {
    val id: UUID = UUID.randomUUID()
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