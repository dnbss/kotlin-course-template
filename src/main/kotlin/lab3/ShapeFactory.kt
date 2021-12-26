package lab3

import kotlin.random.Random


interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(sideA: Double): Square
    fun createRectangle(sideA: Double, sideB: Double): Rectangle
    fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle

    fun createRandomCircle(fromRadius: Double = 1.0, untilRadius: Double = 5.0): Circle
    fun createRandomSquare(fromSide: Double = 1.0, untilSide: Double = 5.0): Square
    fun createRandomRectangle(fromSide: Double = 1.0, untilSide: Double = 5.0): Rectangle
    fun createRandomTriangle(fromSide: Double = 3.0, untilSide: Double = 5.0): Triangle

    fun createRandomShape(fromSide: Double = 1.0, untilSide: Double = 5.0): Shape
}

class ShapeFactoryImpl : ShapeFactory {
    override fun createCircle(radius: Double): Circle = Circle(radius)

    override fun createSquare(sideA: Double): Square = Square(sideA)

    override fun createRectangle(sideA: Double, sideB: Double): Rectangle = Rectangle(sideA, sideB)

    override fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle = Triangle(sideA, sideB, sideC)

    override fun createRandomCircle(fromRadius: Double, untilRadius: Double): Circle {
        checkRangeSides(fromRadius, untilRadius)

        return Circle(Random.nextDouble(fromRadius, untilRadius))
    }

    override fun createRandomSquare(fromSide: Double, untilSide: Double): Square {
        checkRangeSides(fromSide, untilSide)

        return Square(Random.nextDouble(fromSide, untilSide))
    }

    override fun createRandomRectangle(fromSide: Double, untilSide: Double): Rectangle {
        checkRangeSides(fromSide, untilSide)

        val sideA = Random.nextDouble(fromSide, untilSide)
        val sideB = Random.nextDouble(fromSide, untilSide)

        return Rectangle(sideA, sideB)
    }

    override fun createRandomTriangle(fromSide: Double, untilSide: Double): Triangle {
        checkRangeSides(fromSide, untilSide)

        val sideA = Random.nextDouble(fromSide, untilSide)
        val sideB = Random.nextDouble(fromSide, untilSide)
        val angle = Random.nextDouble(0.1, 180.0)

        val sideC = kotlin.math.sqrt(sideA * sideA + sideB * sideB - 2 * sideA * sideB * kotlin.math.cos(angle))

        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomShape(fromSide: Double, untilSide: Double): Shape {
        checkRangeSides(fromSide, untilSide)

        return when ((0..3).random()) {
            0 -> createRandomCircle(fromSide, untilSide)
            1 -> createRandomSquare(fromSide, untilSide)
            2 -> createRandomRectangle(fromSide, untilSide)
            3 -> createRandomTriangle(fromSide, untilSide)
            else -> throw Exception("Invalid random result")
        }
    }

    private fun checkRangeSides(fromSide: Double, untilSide: Double) {
        if (fromSide <= 0) {
            throw IllegalArgumentException("Lower bound must be positive")
        }

        if (fromSide > untilSide) {
            throw IllegalArgumentException("Lower bound must be less than upper bound")
        }
    }
}
