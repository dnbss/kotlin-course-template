import lab3.Circle
import lab3.Rectangle
import lab3.Square
import lab3.Triangle
import lab7.ShapeSerializer
import org.junit.Assert
import org.junit.Test

class ShapeSerializerTests {

    private val square = Square(2.0)

    private val rectangle = Rectangle(2.0, 4.0)

    private val circle = Circle(2.0)

    private val triangle = Triangle(6.0, 8.0, 10.0)

    private val shapes = listOf(square, rectangle, circle, triangle)

    @Test
    fun EncodeTest_Square_Correct() {
        Assert.assertEquals(
            """
            {
                "side": 2.0
            }
        """.trimIndent(), ShapeSerializer.encode(square)
        )
    }

    @Test
    fun EncodeTest_Circle_Correct() {
        Assert.assertEquals(
            """
            {
                "radius": 2.0
            }
        """.trimIndent(), ShapeSerializer.encode(circle)
        )
    }

    @Test
    fun EncodeTest_Rectangle_Correct() {
        Assert.assertEquals(
            """
            {
                "sideA": 2.0,
                "sideB": 4.0
            }
        """.trimIndent(), ShapeSerializer.encode(rectangle)
        )
    }

    @Test
    fun EncodeTest_Triangle_Correct() {
        Assert.assertEquals(
            """
            {
                "sideA": 6.0,
                "sideB": 8.0,
                "sideC": 10.0
            }
        """.trimIndent(), ShapeSerializer.encode(triangle)
        )
    }

    @Test
    fun EncodeTest_listShapes_Correct() {
        Assert.assertEquals(
            """
            [
                {
                    "type": "lab3.Square",
                    "side": 2.0
                },
                {
                    "type": "lab3.Rectangle",
                    "sideA": 2.0,
                    "sideB": 4.0
                },
                {
                    "type": "lab3.Circle",
                    "radius": 2.0
                },
                {
                    "type": "lab3.Triangle",
                    "sideA": 6.0,
                    "sideB": 8.0,
                    "sideC": 10.0
                }
            ]
        """.trimIndent(), ShapeSerializer.encode(shapes)
        )
    }

    @Test
    fun DecodeTest_Square_Correct() {
        Assert.assertEquals(
            2.0,
            ShapeSerializer.decode<Square>(ShapeSerializer.encode(square)).side,
            0.1
        )
    }

    @Test
    fun DecodeTest_Rectangle_Correct() {
        Assert.assertEquals(
            2.0,
            ShapeSerializer.decode<Rectangle>(ShapeSerializer.encode(rectangle)).sideA,
            0.1
        )
        Assert.assertEquals(
            4.0,
            ShapeSerializer.decode<Rectangle>(ShapeSerializer.encode(rectangle)).sideB,
            0.1
        )
    }

    @Test
    fun Decode_Circe_Correct() {
        Assert.assertEquals(
            2.0,
            ShapeSerializer.decode<Circle>(ShapeSerializer.encode(circle)).radius,
            0.1
        )
    }

    @Test
    fun Decode_Triangle_Correct() {
        Assert.assertEquals(
            6.0,
            ShapeSerializer.decode<Triangle>(ShapeSerializer.encode(triangle)).sideA,
            0.1
        )
        Assert.assertEquals(
            8.0,
            ShapeSerializer.decode<Triangle>(ShapeSerializer.encode(triangle)).sideB,
            0.1
        )
        Assert.assertEquals(
            10.0,
            ShapeSerializer.decode<Triangle>(ShapeSerializer.encode(triangle)).sideC,
            0.1
        )
    }
}