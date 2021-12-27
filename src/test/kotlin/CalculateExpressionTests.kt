import lab2.calculateExpression
import org.junit.Test
import kotlin.test.assertEquals

class CalculateExpressionTests {

    @Test
    fun calculateExpressionTest_SimpleExpressions_Correct(){
        assertEquals(20, calculateExpression("15    +   5"))
        assertEquals(0, calculateExpression("0    -  0"))
        assertEquals(3, calculateExpression("15   /  5"))
        assertEquals(75, calculateExpression("15   *   5"))
    }

    @Test
    fun calculateExpressionTest_HardExpressions_Correct(){
        assertEquals(185, calculateExpression("1 + ( 2 *  10  ^ 2 / 2 * 3 - 56) -60"))
        assertEquals(-1, calculateExpression("-( ( 5 ^ (  1+1 ) + (17 * 3 - 51)  )     /            25)"))
        assertEquals(100, calculateExpression("(30^2    +    100 - 5 *  10 ^ 2) / 5"))
        assertEquals(0, calculateExpression("5^-(  1 + 2  ^  3)   "))
    }

    @Test(expected = Exception::class)
    fun calculateExpression_InvalidExpression_Exception(){
        calculateExpression("(((((0 + 5)))")
        calculateExpression("1 + )5( * 4")
        calculateExpression("(-3+9))*30")
        calculateExpression("+4 */ 33")
        calculateExpression("*3 + 3 ")
        calculateExpression("3+alksdjfklsdjfkldj+asdfd+3")
    }
}