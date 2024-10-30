package service;

import model.BookSummary;
import org.springframework.boot.web.client.RestTemplateBuilder;
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
        String bookServiceUrl = "http://localhost:8081/books/" + id;
        return restTemplate.postForEntity(bookServiceUrl, null, BookSummary.class);
    }


}
