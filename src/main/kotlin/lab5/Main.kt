package lab5

fun main() {
    val library = LibraryServiceImpl()

    val books: MutableMap<Book, Status> = mutableMapOf<Book, Status>(
        Book(
            "BookName1",
            mutableListOf(Author("Author Name1", "Author LastName1")),
            2012,
            Genre.Fantasy
        ) to Status.Restoration,
        Book(
            "BookName2",
            mutableListOf(Author("Author Name2", "Author LastName2")),
            2015,
            Genre.Detective
        ) to Status.Available,
        Book(
            "BookName3",
            mutableListOf(Author("Author Name3", "Author LastName3")),
            2002,
            Genre.Adventure
        ) to Status.ComingSoon,
        Book(
            "BookName4",
            mutableListOf(Author("Author Name4", "Author LastName4")),
            2016,
            Genre.Fantasy
        ) to Status.Available,
        Book(
            "BookName5",
            mutableListOf(Author("Author Name4", "Author LastName4")),
            2017,
            Genre.ScienceFiction
        ) to Status.Available
    )

    books.forEach { bookInfo -> library.addBook(bookInfo.key, bookInfo.value) }

    val users: MutableList<User> = mutableListOf<User>(
        User("Name1", "LastName1"),
        User("Name2", "LastName2"),
        User("Name3", "LastName3"),
        User("Name4", "LastName4")
    )
    users.forEach { user -> library.registerUser(user) }

    println("All books:")
    library.getAllBooks().forEach { println(it) }

    println("\nStatuses of each book")
    library.getAllBookStatuses().forEach { println("${it.key.name} - ${it.value}") }

    println("\nAfter taking books with '3' in name")
    library.takeBook(users[0], library.findBooks("3")[0])
    library.getAllBooks().forEach { println(it) }

    println("\nStatuses of each book")
    library.getAllBookStatuses().forEach { println("${it.key.name} - ${it.value}") }

    println("\nAll available books")
    library.getAllAvailableBooks().forEach { println(it) }

    library.returnBook(library.findBooks("3")[0])

    println("\nUnregister user №3")
    library.unregisterUser(users[2])

    try {
        library.takeBook(users[2], library.getAllBooks()[1])

    } catch (e: Exception) {

        println("\n${users[2]}")
        println("trying take book from library!")
    }

    println("\nAll books with multiple filters:")
    library.findBooks("Book", null, null, Genre.Fantasy, Status.Available).forEach { println(it) }

    library.setBookStatus(library.findBooks(null, null, null, null, Status.Restoration).first(), Status.Available)

    try {
        library.takeBook(users[0], library.getAllAvailableBooks()[0])
        library.takeBook(users[0], library.getAllAvailableBooks()[0])
        library.takeBook(users[0], library.getAllAvailableBooks()[0])
        library.takeBook(users[0], library.getAllAvailableBooks()[0])

    } catch (e: Exception) {

        println(e.message)
    }

    try {
        library.returnBook(library.getAllBooks()[0])
        library.returnBook(library.getAllBooks()[0])

    } catch (e: Exception) {

        println(e.message)
    }
}