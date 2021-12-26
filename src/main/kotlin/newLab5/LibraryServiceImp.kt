package newLab5


class LibraryServiceImp : LibraryService {

    private val countBooksByUser: Int = 3
    private val books: MutableSet<Book> = mutableSetOf()
    private val users: MutableSet<User> = mutableSetOf()
    private val bookStatuses: MutableMap<Book, Status> = mutableMapOf()
    private val userBooks: MutableMap<User, MutableSet<Book>> = mutableMapOf()

    override fun findBooks(substring: String): List<Book> = books.filter { it.name.contains(substring) }

    override fun findBooks(author: Author): List<Book> = books.filter { it.author.contains(author) }

    override fun findBooks(year: Int): List<Book> = books.filter { it.year == year }

    override fun findBooks(genre: Genre): List<Book> = books.filter { it.genre == genre }

    override fun findBooks(status: Status): List<Book> = bookStatuses.filter { it.value == status }.keys.toList()

    override fun findBooks(
        substring: String?,
        author: Author?,
        year: Int?,
        genre: Genre?,
        status: Status?
    ): List<Book> {
        var unionBooks = setOf<Book>()

        if (substring != null)
            unionBooks = unionBooks.union(findBooks(substring))
        if (author != null)
            unionBooks = unionBooks.union(findBooks(author))
        if (year != null)
            unionBooks = unionBooks.union(findBooks(year))
        if (genre != null)
            unionBooks = unionBooks.union(findBooks(genre))
        if (status != null)
            unionBooks = unionBooks.union(findBooks(status))

        return unionBooks.toList()
    }

    override fun getAllBooks(): List<Book> = books.toList()

    override fun getAllAvailableBooks(): List<Book> =
        bookStatuses.filter { it.value == Status.Available }.keys.toList()

    override fun getBookStatus(book: Book): Status {

        val foundBook = books.filter { it == book }

        if (foundBook.isEmpty())
            throw NoSuchElementException("This book is not in the library")

        return bookStatuses[foundBook[0]]!!
    }

    override fun getAllBookStatuses(): Map<Book, Status> = bookStatuses

    override fun setBookStatus(book: Book, status: Status) {
        val foundBook = books.filter { it == book }

        if (foundBook.isEmpty())
            throw NoSuchElementException("This book is not in the library")

        bookStatuses[foundBook[0]] = status
    }

    override fun addBook(book: Book, status: Status) {
        val foundBook = books.filter { it == book }

        if (foundBook.isNotEmpty())
            throw NoSuchElementException("This book is already in this library!")

        books.add(book)

        bookStatuses[book] = status
    }

    private fun isRegistered(user: User): Boolean =
        users.find { it.firstName == user.firstName && it.lastName == user.lastName } != null

    override fun registerUser(firstName: String, lastName: String) {
        val user = User(firstName, lastName)

        if (!isRegistered(user)) {
            users.add(user)

            userBooks[user] = mutableSetOf()
        }
    }

    override fun unregisterUser(user: User) {
        if (isRegistered(user)) {

            if (userBooks[user]!!.isNotEmpty())
                throw Exception(
                    "User must return books: " + userBooks[user]!!.joinToString()
                )

            userBooks.remove(user)

            users.remove(user)
        }
    }

    override fun takeBook(user: User, book: Book) {
        if (!isRegistered(user))
            throw IllegalStateException("This user is not a user of the library!")

        if (userBooks[user]!!.count() >= countBooksByUser)
            throw IllegalStateException("the limit of the number of books has been reached")

        if (bookStatuses[book] != Status.Available)
            throw Exception("This book is not available")

        userBooks[user]!!.add(book)

        bookStatuses[book] = Status.UsedBy(user)
    }

    override fun returnBook(book: Book) {
        books.find { it == book } ?: throw IllegalStateException("Library doesn't contained this book!")

        if (bookStatuses[book] == Status.Available) throw IllegalStateException("book is already available!")

        userBooks.filter { it.value.contains(book) }.values.first().remove(book)

        bookStatuses[book] = Status.Available
    }

}
