package model

import sql.entity.GroupMap
import util.DateUtil
import java.sql.ResultSet
import java.util.*

class Group(var name: String) : Entity() {
    val products = mutableListOf<Product>()

    companion object {
        fun createBuilder(): GroupBuilder {
            return GroupBuilder()
        }

        fun castGroupByResultSet(resultSet: ResultSet, products: List<Product>): Group {
            return createBuilder()
                .setId(UUID.fromString(resultSet.getString(GroupMap.ID.label)))
                .setName(resultSet.getString(GroupMap.NAME.label))
                .setUpdated(resultSet.getDate(GroupMap.UPDATED.label))
                .setCreated(resultSet.getDate(GroupMap.CREATED.label))
                .setProducts(products)
                .build()
        }
    }

    constructor(name: String, products: List<Product>) : this(name) {
        this.products.addAll(products)
    }

    private constructor(id: UUID, name: String, products: List<Product>, updated: Date, created: Date) : this(
        name,
        products
    ) {
        this.id = id
        this.name = name
        this.updated = updated
        this.created = created
    }

    override fun toString(): String {
        return "'$id', '$name', '${DateUtil.getStringDate(created)}', '${DateUtil.getStringDate(updated)}'"
    }

    class GroupBuilder {
        private lateinit var id: UUID
        private lateinit var name: String
        private lateinit var products: List<Product>
        private lateinit var updated: Date
        private lateinit var created: Date

        fun setId(id: UUID): GroupBuilder {
            this.id = id
            return this
        }

        fun setName(name: String): GroupBuilder {
            this.name = name
            return this
        }

        fun setProducts(products: List<Product>): GroupBuilder {
            this.products = products
            return this
        }

        fun setUpdated(updated: Date): GroupBuilder {
            this.updated = updated
            return this
        }

        fun setCreated(created: Date): GroupBuilder {
            this.created = created
            return this
        }

        fun build(): Group {
            return Group(id, name, products, updated, created)
        }
    }
}