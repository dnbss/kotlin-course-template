package lab4

open class Matrix(matrix: Array<Array<Double>>) {

    protected var _matrix: Array<Array<Double>>
    val height: Int
        get() = _matrix.size
    val width: Int
        get() = _matrix[0].size

    init {
        _matrix = Array(matrix.size) { Array(matrix[0].size) { 0.0 } }

        for (row in 0 until height)
            for (column in 0 until width) {
                _matrix[row][column] = matrix[row][column]
            }
    }

    operator fun plus(other: Matrix): Matrix = Matrix(add(other))

    operator fun minus(other: Matrix): Matrix = Matrix(subtract(other))

    operator fun times(scalar: Double): Matrix = Matrix(multiply(scalar))

    operator fun times(other: Matrix): Matrix = Matrix(multiply(other))

    operator fun div(scalar: Double): Matrix = Matrix(divide(scalar))

    operator fun get(row: Int, column: Int): Double = _matrix[row][column]

    operator fun unaryMinus(): Matrix = Matrix(_matrix) * (-1.0)

    operator fun unaryPlus(): Matrix = this

    override operator fun equals(other: Any?): Boolean {
        if (other == null)
            return false

        val otherMatrix = other as Matrix

        if (otherMatrix.height != this.height || otherMatrix.width != this.width)
            return false

        for (row in 0 until height)
            for (column in 0 until width)
                if (_matrix[row][column] != otherMatrix[row, column])
                    return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        var result = ""

        for (row in 0 until height) {
            for (column in 0 until width) {
                result += this[row, column].toString() + " "
            }
            result += "\n"
        }

        return result
    }

    protected fun add(other: Matrix): Array<Array<Double>> {
        if (other.height != this.height || other.width != this.width) {
            throw IllegalArgumentException("Dimension of the other matrix must be equal")
        }

        val result = Array(height) { Array(width) { 0.0 } }

        for (row in 0 until height)
            for (column in 0 until width) {
                result[row][column] = _matrix[row][column] + other[row, column]
            }
        return result
    }

    protected fun subtract(other: Matrix): Array<Array<Double>> {
        if (other.height != this.height || other.width != this.width) {
            throw IllegalArgumentException("Dimension of the other matrix must be equal")
        }

        val result = Array(height) { Array(width) { 0.0 } }

        for (row in 0 until height)
            for (column in 0 until width) {
                result[row][column] = _matrix[row][column] - other[row, column]
            }
        return result
    }

    protected fun multiply(scalar: Double): Array<Array<Double>> {
        val result = Array(height) { Array(width) { 0.0 } }

        for (row in 0 until height)
            for (column in 0 until width) {
                result[row][column] = _matrix[row][column] * scalar
            }
        return result
    }

    protected fun multiply(other: Matrix): Array<Array<Double>> {
        if (width != other.height)
            throw Exception("Incorrect dimensions")

        val result = Array(height) { Array(other.width) { 0.0 } }

        for (row in 0 until height)
            for (columns in 0 until other.width)
                for (i in 0 until width) {
                    result[row][columns] += _matrix[row][i] * other[i, columns]
                }
        return result
    }

    protected fun divide(scalar: Double): Array<Array<Double>> {
        if (scalar == 0.0)
            throw Exception("Cannot be divided by zero")

        val result = Array(height) { Array(width) { 0.0 } }

        for (row in 0 until height)
            for (column in 0 until width) {
                result[row][column] = _matrix[row][column] / scalar
            }
        return result
    }

}
