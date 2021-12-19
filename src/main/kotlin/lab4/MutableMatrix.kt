package lab4

class MutableMatrix(matrix: Array<Array<Double>>) : Matrix(matrix) {

    operator fun plusAssign(other: Matrix) {
        _matrix = add(other)
    }

    operator fun minusAssign(other: Matrix) {
        _matrix = subtract(other)
    }

    operator fun timesAssign(other: Matrix) {
        _matrix = multiply(other)
    }

    operator fun timesAssign(scalar: Double) {
        _matrix = multiply(scalar)
    }

    operator fun divAssign(scalar: Double) {
        _matrix = divide(scalar)
    }

    operator fun set(row: Int, column: Int, value: Double) {
        _matrix[row][column] = value
    }
}