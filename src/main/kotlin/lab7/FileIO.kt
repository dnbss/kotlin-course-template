package lab7

import java.io.File

object FileIO {
    fun fileWriter(value: String, path: String) {
        File(path).writeText(value)
    }

    fun fileReader(path: String): String = File(path).readText()
}