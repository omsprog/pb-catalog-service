package com.omsprog.polarbookshop.catalogservice.demo;

import com.omsprog.polarbookshop.catalogservice.domain.Book;
import com.omsprog.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata") // It will be registered only when the testdata profile is active.
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // The test data generation is triggered when an ApplicationReadyEvent is sent
    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234567891", "Northern Lights",
                "Lyra Silverstar",9.90, "Polarsophia");
        var book2 = Book.of("1234567892", "Polar Journey",
                "Iorek Polarson",9.90, "Polarsophia");

        bookRepository.saveAll(List.of(book1, book2));
    }
}
