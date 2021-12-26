package newLab5


fun main() {

    val libraryService = LibraryServiceImp()

    val books: MutableMap<Book, Status> = mutableMapOf<Book, Status>(
        Book(
            "The Witcher",
            mutableListOf(Author("Andrzej", "Sapkowski")),
            1986,
            Genre.Fantasy
        ) to Status.Restoration,
        Book(
            "The adventures of Sherlock Holmes",
            mutableListOf(Author("Arthur", "Conan Doyle")),
            1900,
            Genre.Adventure
        ) to Status.ComingSoon,
        Book(
            "1984",
            mutableListOf(Author("George", "Orwell")),
            1949,
            Genre.ScienceFiction
        ) to Status.Available
    )

    books.forEach { libraryService.addBook(it.key, it.value) }

    val users: MutableList<User> = mutableListOf(
        User("Esmond", "Ward"),
        User("Alvin", "Kelly"),
        User("Gerard", "Booker"),
        User("Andrew", "Nelson")
    )

    users.forEach { libraryService.registerUser(it.firstName, it.lastName) }

    println("All books:")

    libraryService.getAllBooks().forEach {
        bookInfo(it, libraryService)
    }


    println("\nAfter taking books with '1984' in name")
    libraryService.takeBook(users[0], libraryService.findBooks("1984")[0])

    libraryService.getAllBooks().forEach {
        bookInfo(it, libraryService)
    }

    println("\nUnregister user №3")
    libraryService.unregisterUser(users[2])

    try {
        libraryService.takeBook(users[2], libraryService.getAllBooks()[0])

    } catch (e: Exception) {

        println(e.message)
    }

    println("\nAll books with multiple filters:")
    libraryService.findBooks("Witcher", null, null, Genre.Fantasy, Status.Restoration).forEach {
        bookInfo(it, libraryService)
    }

    println("set book status:\n")
    libraryService.setBookStatus(libraryService.findBooks("Witcher")[0], Status.Available)
    libraryService.getAllBooks().forEach {
        bookInfo(it, libraryService)
    }

    println("returning book:\n")
    libraryService.returnBook(libraryService.findBooks("1984")[0])
    libraryService.getAllBooks().forEach {
        bookInfo(it, libraryService)
    }
}

fun bookInfo(book: Book, libraryService: LibraryService) {
    println("Book name:${book.name}")
    book.author.forEach {
        println("Author:${it.firstName} ${it.lastName}")
    }
    println("Year: ${book.year}")
    println("Genre: ${book.genre}")
    println("Status: ${book.name} - ${libraryService.getBookStatus(book)}\n")
}
