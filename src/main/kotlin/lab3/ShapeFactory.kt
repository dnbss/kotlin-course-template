package lab3

import kotlin.random.Random


interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(sideA: Double): Square
    fun createRectangle(sideA: Double, sideB: Double): Rectangle
    fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle

    fun createRandomCircle(fromRadius: Double, untilRadius: Double): Circle
    fun createRandomSquare(fromSide: Double, untilSide: Double): Square
    fun createRandomRectangle(fromSide: Double, untilSide: Double): Rectangle
    fun createRandomTriangle(fromSide: Double, untilSide: Double): Triangle

    fun createRandomShape(fromSide: Double, untilSide: Double): Shape
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
        var sideC = Random.nextDouble(fromSide, untilSide)

        while (sideA + sideB <= sideC || sideA + sideC <= sideB || sideC + sideB <= sideA) {
            sideC = Random.nextDouble(fromSide, untilSide)
        }

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
