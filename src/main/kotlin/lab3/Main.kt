package lab3

fun main() {
    val shapeFactory: ShapeFactory = ShapeFactoryImpl()

    val listShapes: List<Shape> = listOf(
        shapeFactory.createCircle(7.0),
        shapeFactory.createSquare(6.0),
        shapeFactory.createRectangle(6.0, 3.0),
        shapeFactory.createTriangle(3.0, 4.0, 5.0),
    )
    println("Shapes statistic:")
    printStatistic(listShapes)

    val listRandomParameterShapes: List<Shape> = listOf(
        shapeFactory.createRandomCircle(1.0, 10.0),
        shapeFactory.createRandomSquare(1.0, 10.0),
        shapeFactory.createRandomRectangle(1.0, 10.0),
        shapeFactory.createRandomTriangle(1.0, 10.0),
    )

    println("\nShapes with random parameters statistic:")
    printStatistic(listRandomParameterShapes)

    val listRandomShapes: List<Shape> = listOf(
        shapeFactory.createRandomShape(1.0, 10.0),
        shapeFactory.createRandomShape(1.0, 10.0),
        shapeFactory.createRandomShape(1.0, 10.0),
        shapeFactory.createRandomShape(1.0, 10.0)
    )

    println("\nRandom shapes statistic:")
    printStatistic(listRandomShapes)
}

fun printStatistic(listOfShapes: List<Shape>) {
    println("Sum area: ${listOfShapes.sumOf { it.calcArea() }}")
    println("Sum perimeter: ${listOfShapes.sumOf { it.calcPerimeter() }}")
    println("Shape with min area: ${listOfShapes.minByOrNull { it.calcArea() }}")
    println("Shape with max area: ${listOfShapes.maxByOrNull { it.calcArea() }}")
}