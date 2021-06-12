package model

import util.DateUtil
import java.util.*

class Product(val name: String) : Entity() {
    var price: Float = 0F
    var description: String = ""
    var groups: MutableList<Group> = mutableListOf()
    var section: Section? = null

    constructor(name: String, price: Float) : this(name) {
        this.price = price
    }

    constructor(name: String, price: Float, description: String) : this(name, price) {
        this.description = description
    }

    override fun toString(): String {
        return "'$id', " +
                "'$name', " +
                "'$price', " +
                "'$description', " +
                "'${DateUtil.getDateTime(created)}', " +
                "'${DateUtil.getDateTime(updated)}'"
    }
}