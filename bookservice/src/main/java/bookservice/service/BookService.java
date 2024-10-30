package bookservice.service;

import bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    /* private -> BookRepository is only accessible within BookService
       final -> bookRepository is FINAL - no changes will be done
     */
    private final BookRepository bookRepository;


    /* creating a constructor and injecting the BookRepository
       interface so that we can use it to preform database operations
     */
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // adding a new book to the database
    public Book addingBook(Book book) {
        return bookRepository.save(book);
    }

    // getting all the books from the database
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /* getting a specific book from the database with unique id
     -> we use Optional here because we are fetching an id which is
     supposed to be unique: either we get the d/book or not
     */
    public Optional<Book> findingBookById(Long id) {
        return bookRepository.findById(id);
    }

    // searching for a specific book by the name of its author
    public List<Book> findingBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    // searching for a specific book by its title
    public List<Book> findingBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    /* the user gets to mark a certain book as "borrowed" thanks to
        its book-id
     */
    public Optional<Book> markAsBorrowed(Long id) {
        Optional<Book> foundBook = bookRepository.findById(id);

        /* checking to see if the chosen book-id by user matches the
           one inside the database. If it's found, it is retrieved
         */
        if (foundBook.isPresent()) {
            Book book = foundBook.get();
            /* the book must not already be set to borrowed in order
                for our logic to work
             */
            if (!book.isBorrowed()) {
                book.setBorrowed(true);
                bookRepository.save(book);
            }
            // saving the newly updated book inside the database
            return Optional.of(book);
        } else {
            /* in case the book-id was not found in the first place,
                simply nothing gets returned
             */
            return Optional.empty();
        }
    }

    // a certain book is found by id - which then gets deleted
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}