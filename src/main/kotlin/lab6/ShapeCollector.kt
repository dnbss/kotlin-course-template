package lab6

import lab3.Shape
import lab3.Circle

class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        allShapes.add(new)
    }

    fun addAll(new: List<T> ) {
        allShapes.addAll(new)
    }

    fun getAll(): List<T> = allShapes.toList()

    fun getAllSorted(comparator: Comparator<in T>): List<T> = allShapes.sortedWith(comparator)

    fun getAllByClass(classname: Class<out T>): List<T> = allShapes.filterIsInstance(classname)
}