package lab3

import kotlinx.serialization.Serializable
import kotlin.math.sqrt

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

@Serializable
class Circle(val radius: Double) : Shape {
    init {
        if (radius <= 0)
            throw IllegalArgumentException("Radius must be greater than zero")
    }

    override fun calcArea(): Double = Math.PI * radius * radius

    override fun calcPerimeter(): Double = 2 * Math.PI * radius
}

@Serializable
class Square(val side: Double) : Shape {
    init {
        if (side <= 0)
            throw IllegalArgumentException("Side must be greater than zero")
    }

    override fun calcArea(): Double = side * side

    override fun calcPerimeter(): Double = 4 * side

}

@Serializable
class Rectangle(val sideA: Double, val sideB: Double) : Shape {
    init {
        if (sideA <= 0 || sideB <= 0) {
            throw IllegalArgumentException("Sides must be greater than zero")
        }
    }

    override fun calcArea(): Double = sideA * sideB

    override fun calcPerimeter(): Double = 2 * (sideA + sideB)
}


@Serializable
class Triangle(val sideA: Double, val sideB: Double, val sideC: Double) : Shape {
    init {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw IllegalArgumentException("Sides must be greater than zero")
        }

        if (sideA + sideB <= sideC || sideA + sideC <= sideB || sideC + sideB <= sideA) {
            throw IllegalArgumentException("Any side must be less than the sum other two sides")
        }
    }

    override fun calcArea(): Double {
        val halfPerimeter = calcPerimeter() / 2

        return sqrt(halfPerimeter * (halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC))
    }

    override fun calcPerimeter(): Double = sideA + sideB + sideC
}