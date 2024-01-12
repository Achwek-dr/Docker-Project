package com.bookStore.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class BookTest {
    

@Test
public void testConstructorAndGetters() {
    Book book = new Book(1, "Test Book", "Test Author", "10.00");
    assertEquals(1, book.getId());
    assertEquals("Test Book", book.getName());
    assertEquals("Test Author", book.getAuthor());
    assertEquals("10.00", book.getPrice());
}

@Test
public void testSettersAndGetters() {
    Book book = new Book();
    book.setId(2);
    book.setName("Another Test Book");
    book.setAuthor("Another Test Author");
    book.setPrice("15.00");

    assertEquals(2, book.getId());
    assertEquals("Another Test Book", book.getName());
    assertEquals("Another Test Author", book.getAuthor());
    assertEquals("15.00", book.getPrice());
}

}