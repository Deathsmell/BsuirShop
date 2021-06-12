package model

class Group(name: String) {
    val products = mutableListOf<Product>()

    constructor(name: String, products: Array<Product>): this(name) {
        this.products.addAll(products)
    }
}