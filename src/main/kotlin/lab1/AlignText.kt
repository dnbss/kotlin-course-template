package lab1

enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY
}

fun alignText(
    text: String,
    lineWidth: Int = 120,
    alignment: Alignment = Alignment.LEFT
): String {
    if (lineWidth < 1)
        throw IllegalArgumentException("Incorrect line width")

    val splitText = splitText(text, lineWidth)

    return when (alignment) {
        Alignment.LEFT -> alignLeft(splitText, lineWidth)
        Alignment.RIGHT -> alignRight(splitText, lineWidth)
        Alignment.CENTER -> alignCenter(splitText, lineWidth)
        Alignment.JUSTIFY -> alignJustify(splitText, lineWidth)
    }
}

fun splitText(
    text: String,
    lineWidth: Int
) : MutableList<String>{

    val splitText: MutableList<String> = arrayListOf()
    var indexSplit : Int
    var currentText = text

    while (currentText.isNotEmpty()){
        indexSplit = currentText.indexOf('\n')

        if (!(indexSplit <= lineWidth && indexSplit != -1)){
            if (currentText.length > lineWidth) {
                indexSplit = currentText.lastIndexOf(' ', lineWidth)        // last space that fits in the line

                if (indexSplit == -1) {
                    indexSplit = lineWidth          // word separation
                }
            }
            else{
                indexSplit = currentText.lastIndex + 1
            }
        }

        splitText.add(currentText.substring(0, indexSplit))

        // removing spaces or \n in front of a line
        var i = indexSplit
        while (i < currentText.length && (currentText[i] == ' ' || currentText[i] == '\n'))
            i++

        currentText = currentText.substring(i)
    }

    return splitText
}

private fun alignRight(splitText: MutableList<String>, lineWidth: Int) : String{
    return splitText.joinToString(separator = "\n") {

        it.padStart(lineWidth)
    }
}

private fun alignLeft(splitText: MutableList<String>, lineWidth: Int) : String{
    return splitText.joinToString(separator = "\n") {

        it.padEnd(lineWidth)
    }
}

private fun alignCenter(splitText: MutableList<String>, lineWidth: Int) : String{
    return splitText.joinToString(separator = "\n") {

        val start = lineWidth - (lineWidth - it.length) / 2
        it.padStart(start).padEnd(lineWidth)
    }
}

private fun alignJustify(splitText: MutableList<String>, lineWidth: Int): String {

    val alignText: MutableList<String> = arrayListOf()

    for (i in 0 until splitText.size - 1) {

        val countSpaces = splitText[i].count { it == ' ' }
        val lengthenSpaces = lengthenSpaces(lineWidth - splitText[i].length, countSpaces)

        var s = ""
        var k = 0

        for (j in 0 until splitText[i].length){
            if (splitText[i][j] == ' '){        // change simple space to lengthen
                s += lengthenSpaces[k++]
            }
            else{
                s += splitText[i][j]
            }
        }
        alignText.add(s)
    }
    alignText.add(splitText.last())

    return alignText.joinToString(separator = "\n")
}

// lengthening spaces to remove free space
private fun lengthenSpaces(freeSpace: Int, countSpaces: Int): List<String> {

    val lengthSpaces = Array(countSpaces) { 1 + freeSpace / countSpaces }

    for (i in 0 until freeSpace % countSpaces){
        lengthSpaces[i]++
    }

    val lengthenSpaces = lengthSpaces.map {
        buildString {

            var s = ""

            for (i in 0 until it){
                s += " "
            }

            append(s)
        }
    }

    return lengthenSpaces
}
