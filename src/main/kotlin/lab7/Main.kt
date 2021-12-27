package lab7

import lab3.Shape
import lab3.ShapeFactoryImpl

fun main() {
    val sourcePath =
        "C:\\Users\\DONBASS\\IdeaProjects\\kotlin-course-template\\src\\main\\kotlin\\lab7\\sourceFile.json"
    val resultPath =
        "C:\\Users\\DONBASS\\IdeaProjects\\kotlin-course-template\\src\\main\\kotlin\\lab7\\resultFile.json"

    val shapeFactory = ShapeFactoryImpl()

    val shapeSerializer = ShapeSerializer<Shape>()

    val decodedListOfShapes = shapeSerializer.decode(FileIO.fileReader(sourcePath))

    decodedListOfShapes.toMutableList().addAll(
        mutableListOf(
            shapeFactory.createCircle(10.0),
            shapeFactory.createSquare(20.0),
        )
    )

    FileIO.fileWriter(shapeSerializer.encode(decodedListOfShapes), resultPath)

    println("Source file content:\n${FileIO.fileReader(sourcePath)}\n")
    println("Result file content:\n${FileIO.fileReader(resultPath)}")
}