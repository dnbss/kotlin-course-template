package lab4

fun main() {
    val matrix1 = MutableMatrix(Array(2) { Array(3) { 3.0 } })
    var matrix2 = MutableMatrix(Array(2) { Array(3) { 3.0 } })

    println("matrix1 = \n$matrix1")
    println("matrix2 = \n$matrix1")
    println("(1,0) element =  ${matrix1[1, 0]}")
    println("count rows = ${matrix1.height}")
    println("count columns = ${matrix1.width}\n")

    println("equals matrix1 and matrix2 = ${matrix1 == matrix2}\n")

    matrix1[1, 0] = 5.0
    println("(1,0) element changed =  ${matrix1[1, 0]}\n")

    println("sum matrix = \n${matrix1 + matrix2}")
    matrix1 += matrix2
    println("assign plus matrix1 = \n$matrix1")

    println("subtract matrix = \n${matrix1 - matrix2}")
    matrix2 -= matrix1
    println("assign minus matrix2 = \n$matrix2")

    println("multiply matrix =\n${matrix1 * 4.0}")
    matrix1 *= 2.0
    println("assign multiply matrix1 = \n$matrix1")

    matrix2 = MutableMatrix(Array(3) { Array(2) { 3.0 } })
    println("multiply matrices = \n${matrix1 * matrix2}")
    matrix1 *= matrix2
    println("assign multiply matrix1 = \n$matrix1")

    println("div matrix =\n${matrix1 / 5.0}")
    matrix1 /= 3.0
    println("assign div matrix1 = \n$matrix1")

    println("unary plus =\n${+matrix1}")

    println("unary minus =\n${-matrix1}")

}