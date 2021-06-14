package model

import java.util.*

open class Entity {
    var id: UUID = UUID.randomUUID()
    var created: Date = Date()
    var updated: Date = Date()
}