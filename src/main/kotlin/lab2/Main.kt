package lab2

fun main(){
    val s = "-(-100-10)/2*3^(1)".replace(" ","")

    val t = tokenizeString(s)
    t.forEach{println(it)}

    val f = calculateExpression(s)
    println(f)

}