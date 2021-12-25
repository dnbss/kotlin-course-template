package lab5

class LibraryServiceImpl : LibraryService {

    private val books: MutableSet<Book> = mutableSetOf()
    private val users: MutableSet<User> = mutableSetOf()
    private val bookStatuses: MutableMap<Book, Status> = mutableMapOf()
    private val userBooks: MutableMap<User, MutableSet<Book>> = mutableMapOf()

    private val countBooksByUser: Int = 3

    override fun findBooks(
        substring: String?,
        author: Author?,
        year: Int?,
        genre: Genre?,
        status: Status?
    ): List<Book> {

        val filters = listOf<(Book) -> Boolean>(
            { substring == null || it.name.contains(substring) },
            { author == null || it.author.contains(author) },
            { year == null || it.year == year },
            { genre == null || it.genre == genre },
            { status == null || bookStatuses[it] == status },
        )

        return books.filter { filters.all { filter -> filter(it) } }
    }

    override fun findBooks(substring: String): List<Book> = books.filter { it.name.contains(substring) }

    override fun findBooks(author: Author): List<Book> = books.filter { it.author.contains(author) }

    override fun findBooks(year: Int): List<Book> = books.filter { it.year == year }

    override fun findBooks(genre: Genre): List<Book> = books.filter { it.genre == genre }

    override fun getAllBooks(): List<Book> = books.toList()

    override fun getAllAvailableBooks(): List<Book> = books.filter { bookStatuses[it] == Status.Available }

    override fun getBookStatus(book: Book): Status {
        val libraryBook = books.find { item -> item.name == book.name }
        libraryBook
            ?: throw NoSuchElementException("This book is not in the library")

        return bookStatuses[libraryBook]!!
    }

    override fun getAllBookStatuses(): Map<Book, Status> = bookStatuses

    override fun setBookStatus(book: Book, status: Status) {
        books.find { it == book }
            ?: throw IllegalStateException("Library doesn't contain this book!")

        bookStatuses[book] = status
    }

    override fun addBook(book: Book, status: Status) {
        if (books.find { it == book } != null) {
            throw IllegalStateException("This book is already in this library!")
        }

        bookStatuses[book] = status

        books.add(book)
    }

    override fun registerUser(user: User) {
        if (isRegistered(user))
            throw IllegalStateException("This user exist in library!")

        users.add(user)

        userBooks[user] = mutableSetOf()
    }

    private fun isRegistered(user: User): Boolean = users.find { it == user } != null

    override fun unregisterUser(user: User) {
        if (!isRegistered(user))
            throw IllegalStateException("This user not exist in library!")

        val libraryUser = users.find { it == user }

        if (userBooks[libraryUser]!!.size > 0)          // Check if user has books to return
            throw RuntimeException(
                "User must return books: " + userBooks[libraryUser]!!.joinToString()
            )

        users.remove(user)
    }

    override fun takeBook(user: User, book: Book) {
        if (!isRegistered(user))
            throw IllegalStateException("This user is not a user of the library!")

        if (userBooks[user]!!.count() >= countBooksByUser) {
            throw IllegalStateException("Maximum books by user: ${countBooksByUser}. $user have ${userBooks[user]!!.count()}")
        }

        userBooks[user]!!.add(book)

        bookStatuses[book] = Status.UsedBy(user)
    }

    override fun returnBook(book: Book) {
        books.find { it == book } ?: throw IllegalStateException("Library doesn't contained this book!")

        if (bookStatuses[book] == Status.Available) throw IllegalStateException("$book is already available!")

        userBooks.filter { it.value.contains(book) }.map { it.value }.first().remove(book)

        bookStatuses[book] = Status.Available
    }
}