import lab1.Alignment
import lab1.alignText

fun main() {

    val s: String = """
                        SALT II was a series of talks between United States,
                        and Soviet negotiators from 1972 to 1979,
                        it was a continuation of the SALT I talks.
                    """.trimIndent()

    val lineWidth = 30

    println("align right:\n" + alignText(s, lineWidth, Alignment.RIGHT))
    println("\nalign left:\n" + alignText(s, lineWidth, Alignment.LEFT))
    println("\nalign center:\n" + alignText(s, lineWidth, Alignment.CENTER))
    println("\nalign justify:\n" + alignText(s, lineWidth, Alignment.JUSTIFY))
    println("\nword wrapping by characters:\n" + alignText("pneumonoultramicroscopicsilicovolcanoconiosis", 10))

}