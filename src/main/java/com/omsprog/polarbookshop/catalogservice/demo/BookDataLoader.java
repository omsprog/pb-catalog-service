package com.omsprog.polarbookshop.catalogservice.demo;

import com.omsprog.polarbookshop.catalogservice.domain.Book;
import com.omsprog.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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
        var book1 = new Book("1234567891", "Northern Lights",
                "Lyra Silverstar", 9.90);
        var book2 = new Book("1234567892", "Polar Journey",
                "Iorek Polarson", 12.90);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
