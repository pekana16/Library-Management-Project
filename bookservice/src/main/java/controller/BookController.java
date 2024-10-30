package controller;

import model.Book;
import service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    // creating a constructor
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // handling POST requests so that new books can be added
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addingBook(book);
    }

    /* Handling POST requests so that the book is set to be borrowed
       when the user chooses its id
      -> if the wrong book-id is given, 404 error is shown
    */
    @PostMapping("/{id}")
    public ResponseEntity<Book> markAsBorrowed(@PathVariable Long id) {
        Optional<Book> bookBorrowed = bookService.markAsBorrowed(id);
        return bookBorrowed
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // handling GET requests so that all books are shown
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    /* handling GET requests so that a specific books is shown based
        on the chosen book-id
    */
    // took slightly longer than I expected, but it works now
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findingBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /* handling GET requests so that a specific book is shown based
        on the title of the book
    */
    @GetMapping("/search/title")
    public List<Book> findByTitle(@RequestParam String title) {
        return bookService.findingBookByTitle(title);
    }

    /* handling GET requests so that a specific book is shown based
        on the book's author
    */
    @GetMapping("/search/author")
    public List<Book> findByAuthor(@RequestParam String author) {
        return bookService.findingBookByAuthor(author);
    }

    /* handling DELETE requests so that a specific book is deleted from
      the database with the help of its book-id
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }


}
