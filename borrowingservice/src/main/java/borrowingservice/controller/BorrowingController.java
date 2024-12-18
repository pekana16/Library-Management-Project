package borrowingservice.controller;


import borrowingservice.model.BookSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import borrowingservice.service.BorrowingService;

@RestController
@RequestMapping("/borrow")
/* the borrowing function wasnt working on front-end - it was solved with the use of @CrossOrigin
     -> it simply allows requests from "http://localhost:8081"
*/
@CrossOrigin(origins = "http://localhost:8081")
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