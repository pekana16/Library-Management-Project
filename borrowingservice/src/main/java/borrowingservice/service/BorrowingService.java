package borrowingservice.service;

import borrowingservice.model.BookSummary;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BorrowingService {
    private final RestTemplate restTemplate;


    public BorrowingService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ResponseEntity<BookSummary> borrowBook(Long id) {
        String bookServiceUrl = "http://localhost:8081/books/borrow/" + id;
        /* added "HttpMethod.PUT" because we would be using RestTemplate
                  to send a PUT request to BookService
         */
        return restTemplate.exchange(bookServiceUrl, HttpMethod.PUT, null, BookSummary.class);
    }


}
