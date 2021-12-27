package lab2

fun main() {
    val s = listOf<String>(
        "5^-(1 + 2^3)",
        "-(-100-10)/2*3^(1)",
        "(17 * 3 - 3) / 6"
    )


    s.forEach {
        println("$it = ${calculateExpression(it)}")
    }
}