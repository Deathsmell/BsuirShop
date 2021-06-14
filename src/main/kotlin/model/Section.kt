package model

import util.DateUtil
import java.util.*

class Section(var name: String) : Entity() {
    var products: Collection<Product> = mutableListOf()
    
    companion object {
        fun createBuilder(): SectionBuilder {
            return SectionBuilder()
        }
    }

    constructor(name: String, products: Collection<Product>) : this(name) {
        this.products = products
        products.forEach {
            it.section = this
        }
    }

    constructor(id: UUID, name: String, products: Collection<Product>, created: Date, updated: Date) : this(
        name,
        products
    ) {
        this.id = id
        this.name = name
        this.products = products
        this.created = created
        this.updated = updated
    }

    override fun toString(): String {
        return "'$id', '$name', '${DateUtil.getDateTime(created)}', '${DateUtil.getDateTime(updated)}'"
    }

    class SectionBuilder {
        private lateinit var id: UUID
        private lateinit var name: String
        private lateinit var created: Date
        private lateinit var updated: Date
        private lateinit var products: Collection<Product>

        fun setId(id: UUID): SectionBuilder {
            this.id = id
            return this
        }

        fun setName(name: String): SectionBuilder {
            this.name = name
            return this
        }

        fun setCreated(created: Date): SectionBuilder {
            this.created = created
            return this
        }

        fun setUpdated(updated: Date): SectionBuilder {
            this.updated = updated
            return this
        }

        fun setProducts(products: Collection<Product>): SectionBuilder {
            this.products = products
            return this
        }

        fun build(): Section {
            return Section(id, name, products, created, updated)
        }
    }
}