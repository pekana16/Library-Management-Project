package borrowingservice.controller;


import borrowingservice.model.BookSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import borrowingservice.service.BorrowingService;

@RestController
@RequestMapping("/borrow")
public class BorrowingController {
    private final BorrowingService borrowingService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    /* Handling POST requests so that the book is set to be borrowed
       when the user chooses its id
      -> if the wrong book-id is given, 404 error is shown
    */
    @PostMapping("/{id}")
    public ResponseEntity<BookSummary> markAsBorrowed(@PathVariable Long id) {
        return borrowingService.borrowBook(id);
    }

}