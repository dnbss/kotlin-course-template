import lab3.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ShapeFactoryTests {
    companion object {
        val shapeFactoryForException: ShapeFactory = ShapeFactoryImpl()

        private val shapeFactory: ShapeFactory = ShapeFactoryImpl()
        val circle: Circle = shapeFactory.createCircle(6.0)
        val square: Square = shapeFactory.createSquare(4.0)
        val rectangle: Rectangle = shapeFactory.createRectangle(5.0, 2.0)
        val triangle: Triangle = shapeFactory.createTriangle(6.0, 8.0, 10.0)
    }

    @Test
    fun calcArea_Circle_CorrectResult() {
        assertEquals(circle.calcArea(), 113.09733552923255)
    }

    @Test
    fun calcPerimeter_Circle_CorrectResult() {
        assertEquals(circle.calcPerimeter(), 37.69911184307752)
    }

    @Test
    fun calcArea_Square_CorrectResult() {
        assertEquals(square.calcArea(), 16.0)
    }

    @Test
    fun calcPerimeter_Square_CorrectResult() {
        assertEquals(square.calcPerimeter(), 16.0)
    }

    @Test
    fun calcArea_Rectangle_CorrectResult() {
        assertEquals(rectangle.calcArea(), 10.0)
    }

    @Test
    fun calcPerimeter_Rectangle_CorrectResult() {
        assertEquals(rectangle.calcPerimeter(), 14.0)
    }

    @Test
    fun calcArea_Triangle_CorrectResult() {
        assertEquals(triangle.calcArea(), 24.0)
    }

    @Test
    fun calcPerimeter_Triangle_CorrectResult() {
        assertEquals(triangle.calcPerimeter(), 24.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createCircle_inCorrectInstanceCreate_ExceptionExpected() {
        shapeFactoryForException.createCircle(-1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createSquare_inCorrectInstanceCreate_ExceptionExpected() {
        shapeFactoryForException.createSquare(-1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRectangle_NegativeParameter_ExceptionExpected() {
        shapeFactoryForException.createRectangle(-1.0, 2.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createTriangle_OneSideLarger_ExceptionExpected() {
        shapeFactoryForException.createTriangle(1.0, 2.0, 3.2)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createTriangle_NegativeParameter_ExceptionExpected() {
        shapeFactoryForException.createTriangle(-3.2, 1.0, 2.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRandomCircle_NegativeBound_ExceptionExpected() {
        shapeFactoryForException.createRandomCircle(-3.2, 1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRandomCircle_UpperBoundLessLowerBound_ExceptionExpected() {
        shapeFactoryForException.createRandomCircle(3.0, 1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRandomSquare_NegativeBound_ExceptionExpected() {
        shapeFactoryForException.createRandomSquare(-3.1, 1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRandomSquare_UpperBoundLessLowerBound_ExceptionExpected() {
        shapeFactoryForException.createRandomSquare(3.0, 1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRandomRectangle_NegativeBound_ExceptionExpected() {
        shapeFactoryForException.createRandomRectangle(-3.1, 1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRandomRectangle_UpperBoundLessLowerBound_ExceptionExpected() {
        shapeFactoryForException.createRandomRectangle(3.0, 1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRandomTriangle_NegativeBound_ExceptionExpected() {
        shapeFactoryForException.createRandomTriangle(-3.1, 1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRandomTriangle_UpperBoundLessLowerBound_ExceptionExpected() {
        shapeFactoryForException.createRandomTriangle(3.1, 1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRandomShape_NegativeBound_ExceptionExpected() {
        shapeFactoryForException.createRandomShape(-3.1, 1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createRandomShape_UpperBoundLessLowerBound_ExceptionExpected() {
        shapeFactoryForException.createRandomShape(3.0, 1.0)
    }
}