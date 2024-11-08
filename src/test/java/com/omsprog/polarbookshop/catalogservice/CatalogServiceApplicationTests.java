package com.omsprog.polarbookshop.catalogservice;

import com.omsprog.polarbookshop.catalogservice.domain.Book;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT // Loads a full Spring web application context and a Servlet container listening on a random port
)
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated() {
		var expectedBook = Book.of("1231231231", "Title", "Author", 9.90, "Polarsophia");

		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange() // sends the request
				.expectStatus().isCreated() // 201
				.expectBody(Book.class).value(actualBook -> {
					assertThat(actualBook).isNotNull();
					assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
				});
	}
}