package model

import sql.entity.ProductMap
import sql.entity.SectionMap
import util.DateUtil
import java.sql.ResultSet
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

        fun castProductByResultSet(resultSet: ResultSet, section: Section?): Product {
            val id = getIdByResultSet(resultSet)
            return createBuilder()
                .setId(id)
                .setName(resultSet.getString(ProductMap.NAME.label))
                .setPrice(resultSet.getFloat(ProductMap.PRICE.label))
                .setCreated(resultSet.getDate(ProductMap.CREATED.label))
                .setUpdated(resultSet.getDate(ProductMap.UPDATED.label))
                .setDescription(resultSet.getString(ProductMap.DESCRIPTION.label))
                .setSection(section)
                .build()
        }

        fun getIdByResultSet(resultSet: ResultSet): UUID {
            return UUID.fromString(resultSet.getString(SectionMap.ID.label))
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
        section: Section?,
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
                "'${DateUtil.getStringDate(created)}', " +
                "'${DateUtil.getStringDate(updated)}'"
    }

    class ProductBuilder {
        private lateinit var id: UUID
        private lateinit var name: String
        private var price: Float = 0F
        private var description: String = ""
        private val groups: MutableList<Group> = mutableListOf()
        private var section: Section? = null
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

        fun setGroups(groups: List<Group>): ProductBuilder {
            this.groups.addAll(groups)
            return this
        }

        fun setSection(section: Section?): ProductBuilder {
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