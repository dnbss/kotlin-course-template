package lab5

enum class Genre {
    ScienceFiction,
    Adventure,
    Detective,
    Fantasy
}

data class Book(val name: String, val author: List<Author>, val year: Int, val genre: Genre)

data class Author(val firstName: String, val lastName: String)

data class User (val firstName: String, val lastName: String)

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
}

