package lab1

enum class Alignment {
    LEFT,
    RIGHT,
    CENTER
}

fun alignText(
    text: String,
    lineWidth: Int = 120,
    alignment: Alignment = Alignment.LEFT
): String {

    val splitText = splitText(text, lineWidth)

    return when (alignment) {
        Alignment.LEFT -> alignLeft(splitText, lineWidth)
        Alignment.RIGHT -> alignRight(splitText, lineWidth)
        Alignment.CENTER -> alignCenter(splitText, lineWidth)
    }
}

fun splitText(
    text: String,
    lineWidth: Int
) : MutableList<String>{

    var splitText: MutableList<String> = arrayListOf()
    var indexSplit : Int;
    var currentText = text;

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

private fun alignRight(alignText: MutableList<String>, lineWidth: Int) : String{
    return alignText.joinToString(separator = "\n") {

        it.padStart(lineWidth)
    }
}

private fun alignLeft(alignText: MutableList<String>, lineWidth: Int) : String{
    return alignText.joinToString(separator = "\n") {

        it.padEnd(lineWidth)
    }
}

private fun alignCenter(alignText: MutableList<String>, lineWidth: Int) : String{
    return alignText.joinToString(separator = "\n") {

        val start = lineWidth - (lineWidth - it.length) / 2
        it.padStart(start).padEnd(lineWidth)
    }
}
