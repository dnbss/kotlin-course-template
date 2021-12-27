package lab2

import java.util.*
import kotlin.math.pow


private fun tokenizeString(s: String): List<String> {
    val tokenList: ArrayList<String> = arrayListOf()

    val lexemes = listOf('*', '/', '^', '-', '+', '(', ')')

    var i = 0
    while (i < s.length) {

        if (s[i].isDigit()) {
            var number = ""

            while (i < s.length && s[i].isDigit()) {
                number += s[i]
                i++
            }
            tokenList.add(number)

        } else if (lexemes.contains(s[i])) {

            if (s[i] == '-') {
                if (i == 0 || (!s[i - 1].isDigit() && s[i - 1] != ')')) {
                    tokenList.add("u-")
                } else {
                    tokenList.add("b-")
                }

            } else if (s[i] == '+') {
                if (i == 0 || (!s[i - 1].isDigit() && s[i - 1] != ')')) {
                    tokenList.add("u+")
                } else {
                    tokenList.add("b+")
                }

            } else {
                tokenList.add(s[i].toString())
            }

            i++

        } else {
            throw Exception("Invalid character: ${s[i]} on position ${i + 1}")
        }

    }

    return tokenList.toList()
}

fun calculateExpression(s: String): Int {

    val tokenList = tokenizeString(s.replace(" ", ""))

    val numbers = Stack<Int>()
    val operations = Stack<String>()

    for (i in tokenList.indices) {

        try {

            numbers.push(tokenList[i].toInt())

        } catch (e: Exception) {

            if (operations.isEmpty()) {
                operations.add(tokenList[i])
                continue
            }

            if (tokenList[i] == "(") {
                operations.push(tokenList[i])
                continue
            }
            if (tokenList[i] == ")") {
                try {
                    while (operations.peek() != "(") {
                        calculateSubExpression(operations, numbers)
                    }
                    operations.pop()
                } catch (e: Exception) {
                    throw Exception("invalid expression")
                }
                continue
            }

            while (!operations.isEmpty() && operations.peek() != "(" && operatorOrder(operations.peek()) >= operatorOrder(
                    tokenList[i]
                )
            ) {
                calculateSubExpression(operations, numbers)
            }
            if (operations.isEmpty() || (!operations.isEmpty() && operations.peek() == "(") || (operatorOrder(operations.peek()) < operatorOrder(
                    tokenList[i]
                ))
            ) {
                operations.push(tokenList[i])
            }
        }
    }

    try {
        while (!operations.isEmpty()) {
            calculateSubExpression(operations, numbers)
        }
    } catch (e: Exception) {
        throw Exception("invalid expression")
    }

    return numbers.peek()
}

private fun calculateSubExpression(operations: Stack<String>, numbers: Stack<Int>) {
    val operator = operations.pop()

    if (operator.contains("u")) {
        numbers.push(calculateUnaryOperation(numbers.pop(), operator))
    } else {
        val secondNumber = numbers.pop()
        val firstNumber = numbers.pop()

        numbers.push(calculateBinaryOperation(firstNumber, secondNumber, operator))
    }
}

private fun calculateUnaryOperation(x: Int, unaryOperator: String): Int {
    return when (unaryOperator) {
        "u-" -> -x
        "u+" -> x
        else -> throw Exception("Invalid token $unaryOperator")
    }
}

private fun calculateBinaryOperation(x: Int, y: Int, binaryOperator: String): Int {
    return when (binaryOperator) {
        "b+" -> x + y
        "b-" -> x - y
        "*" -> x * y
        "/" -> x / y
        "^" -> x.toDouble().pow(y).toInt()
        else -> throw Exception("Invalid token $binaryOperator")
    }
}

private fun operatorOrder(s: String): Int {
    return when (s) {
        "b+" -> 1
        "b-" -> 1
        "*" -> 2
        "/" -> 2
        "u-" -> 4
        "u+" -> 4
        "^" -> 3
        else -> throw Exception("Invalid token $s")
    }
}

