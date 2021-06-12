package model

import java.util.*

class Product (val name: String){
    var price: Float = 0F
    var description: String = ""
    var groups: MutableList<Group> = mutableListOf()
    var section: Section? = null
    val create: Date = Date()
    var updated: Date = Date()

    constructor(name: String, price: Float): this(name) {
        this.price = price
    }

    constructor(name: String, price: Float, description: String): this(name, price){
        this.description = description
    }
}