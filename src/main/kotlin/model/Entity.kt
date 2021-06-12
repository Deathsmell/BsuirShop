package model

import java.util.*

open class Entity {
    val id: UUID = UUID.randomUUID()
    val created: Date = Date()
    var updated: Date = Date()
}