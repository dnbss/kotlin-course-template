package lab3

import kotlin.math.sqrt

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle(private val _radius: Double) : Shape {
    init {
        if (_radius <= 0)
            throw IllegalArgumentException("Radius must be greater than zero")
    }

    override fun calcArea(): Double = Math.PI * _radius * _radius

    override fun calcPerimeter(): Double = 2 * Math.PI * _radius
}


class Square(private val _side: Double) : Shape {
    init {
        if (_side <= 0)
            throw IllegalArgumentException("Side must be greater than zero")
    }

    override fun calcArea(): Double = _side * _side

    override fun calcPerimeter(): Double = 4 * _side

}


class Rectangle(private val _sideA: Double, private val _sideB: Double) : Shape {
    init {
        if (_sideA <= 0 || _sideB <= 0) {
            throw IllegalArgumentException("Sides must be greater than zero")
        }
    }

    override fun calcArea(): Double = _sideA * _sideB

    override fun calcPerimeter(): Double = 2 * (_sideA + _sideB)
}


class Triangle(private val _sideA: Double, private val _sideB: Double, private val _sideC: Double) : Shape {
    init {
        if (_sideA <= 0 || _sideB <= 0 || _sideC <= 0) {
            throw IllegalArgumentException("Sides must be greater than zero")
        }

        if (_sideA + _sideB <= _sideC || _sideA + _sideC <= _sideB || _sideC + _sideB <= _sideA) {
            throw IllegalArgumentException("Any side must be less than the sum other two sides")
        }
    }

    private val halfPerimeter = calcPerimeter() / 2

    override fun calcArea(): Double =
        sqrt(halfPerimeter * (halfPerimeter - _sideA) * (halfPerimeter - _sideB) * (halfPerimeter - _sideC))

    override fun calcPerimeter(): Double = _sideA + _sideB + _sideC
}