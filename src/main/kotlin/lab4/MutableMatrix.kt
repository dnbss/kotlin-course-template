package lab4

class MutableMatrix(matrix: Array<Array<Double>>) : Matrix(matrix) {

    operator fun plusAssign(other: Matrix) {
        matrix = add(other)
    }

    operator fun minusAssign(other: Matrix) {
        matrix = subtract(other)
    }

    operator fun timesAssign(other: Matrix) {
        matrix = multiply(other)
    }

    operator fun timesAssign(scalar: Double) {
        matrix = multiply(scalar)
    }

    operator fun divAssign(scalar: Double) {
        matrix = divide(scalar)
    }

    operator fun set(row: Int, column: Int, value: Double) {
        matrix[row][column] = value
    }
}