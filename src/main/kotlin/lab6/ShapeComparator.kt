package lab6

import lab3.Circle
import lab3.Shape

object ShapeComparators {
    val compareByArea = compareBy<Shape> { it.calcArea() }

    val compareByAreaDesc = compareByDescending<Shape> { it.calcArea() }

    val compareByPerimeter = compareBy<Shape> { it.calcPerimeter() }

    val compareByPerimeterDesc = compareByDescending<Shape> { it.calcPerimeter() }

    val compareByRadius = compareBy<Circle> { it.radius }

    val compareByRadiusDesc = compareByDescending<Circle> { it.radius }
}