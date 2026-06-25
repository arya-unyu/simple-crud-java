package com.simple.crud;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arraylist;
import java.util.List;

@RestController
@RequestMapping("/api/books")

public class BookController {
    private List<Book> books = new ArrayList<>();
    public BookController() {
        books.add(new Book(1, "Trial 1"));
        books.add(new Book(2, "bagian 2"));
    }
}

@GetMapping
public List<Book> getAllBooks() {
    return books;
}

@GetMapping
public ResponseEntity<Object> getBookById(@PathVariable int id) {
    for(Book book : books) {
        if(book.getId() == id) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }
    return new ResponseEntity<>("Buku tidak ditemukan", HttpStatus.NOT_FOUND);
}

@HttpPost
public ResponseEntity<Object> createbook(@RequestBody Book newBook) {
    int nextId = books.isEmpty() ? 1 : books.get(books.size() - 1).getId() + 1;
    newBook.setId(nextId);
    books.add(newBook);
    return new ResponseEntity<>("Buku berhasil ditambahkan", httpStatus.CREATED);
}

@PutMapping("/{id}")
public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
    for(Book book : books) {
        if(book.getId() == id) {
            if(updatedBook.getTitle() != null) book.setTitle(updatedBook.getTitle());
            if(updatedBook.getAuthor() != null) book.setAuthor(updatedBook.getAuthor());
            return new ResponseEntity<>("Buku berhasil diperbarui", HttpStatus.OK);
        }
    }
    return new ResponseEntity<>("Buku tidak ditemukan", httpStatus.NOT_FOUND);
}

@DeleteMapping