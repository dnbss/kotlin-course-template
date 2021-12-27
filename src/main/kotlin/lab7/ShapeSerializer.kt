package lab7

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import lab3.*
import kotlin.reflect.KClass

class ShapeSerializer<T>{
    val json = Json {
        prettyPrint = true

        serializersModule = SerializersModule {
            polymorphic(Shape::class) {
                subclass(Square::class)
                subclass(Rectangle::class)
                subclass(Circle::class)
                subclass(Triangle::class)
            }
        }
    }

    fun encode(value: List<T>): String = json.encodeToString(value)

    fun decode(string: String): List<T> = json.decodeFromString(string)
}