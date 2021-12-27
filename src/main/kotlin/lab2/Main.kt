package lab2

fun main() {
    val s = listOf<String>(

        "5^-(1 + 2^3)"
    )


    s.forEach{
        println("$it = ${calculateExpression(it)}")
    }
}