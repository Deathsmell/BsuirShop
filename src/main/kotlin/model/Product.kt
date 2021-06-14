package model

import util.DateUtil
import java.util.*

class Product(val name: String) : Entity() {
    var price: Float = 0F
    var description: String = ""
    var groups: MutableList<Group> = mutableListOf()
    var section: Section? = null

    companion object {
        fun createBuilder(): ProductBuilder {
            return ProductBuilder()
        }
    }

    constructor(name: String, price: Float) : this(name) {
        this.price = price
    }

    constructor(name: String, price: Float, description: String) : this(name, price) {
        this.description = description
    }

    constructor(
        id: UUID,
        name: String,
        price: Float,
        description: String,
        groups: MutableList<Group>,
        section: Section,
        updated: Date,
        created: Date
    ) : this(name, price, description) {
        this.id = id
        this.groups = groups
        this.section = section
        this.updated = updated
        this.created = created
    }

    override fun toString(): String {
        return "'$id', " +
                "'$name', " +
                "'$price', " +
                "'$description', " +
                "'${DateUtil.getDateTime(created)}', " +
                "'${DateUtil.getDateTime(updated)}'"
    }

    class ProductBuilder {
        private lateinit var id: UUID
        private lateinit var name: String
        private var price: Float = 0F
        private var description: String = ""
        private var groups: MutableList<Group> = mutableListOf()
        private lateinit var section: Section
        private lateinit var updated: Date
        private lateinit var created: Date

        fun setId(id: UUID): ProductBuilder {
            this.id = id
            return this
        }

        fun setName(name: String): ProductBuilder {
            this.name = name
            return this
        }

        fun setPrice(price: Float): ProductBuilder {
            this.price = price
            return this
        }

        fun setDescription(description: String): ProductBuilder {
            this.description = description
            return this
        }

        fun setGroups(groups: MutableList<Group>): ProductBuilder {
            this.groups = groups
            return this
        }

        fun setSection(section: Section): ProductBuilder {
            this.section = section
            return this
        }

        fun setCreated(created: Date): ProductBuilder {
            this.created = created
            return this
        }

        fun setUpdated(updated: Date): ProductBuilder {
            this.updated = updated
            return this
        }

        fun build(): Product {
            return Product(id, name, price, description, groups, section, updated, created)
        }
    }
}