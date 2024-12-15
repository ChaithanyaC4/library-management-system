package com.library.lms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.lms.entity.Book;
import com.library.lms.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
	 @Autowired
	    private BookRepository bookRepository;

	    public Book createBook(Book book) {
	        return bookRepository.save(book);
	    }

	    public Optional<Book> getBookById(Long id) {
	        return bookRepository.findById(id);
	    }

	    public void deleteBook(Long id) {
	        bookRepository.deleteById(id);
	    }

	    public List<Book> getAllBooks() {
	        return bookRepository.findAll();
	    }

	    public List<Book> searchBooks(String keyword) {
	        return bookRepository.findAll().stream()
	                .filter(book -> book.getTitle().contains(keyword) ||
	                               book.getAuthor().contains(keyword) ||
	                               book.getGenre().contains(keyword))
	                .toList();
	    }
	    public List<Book> getBooksByAuthor(String author) { // Highlighted
	        return bookRepository.findByAuthor(author);
	    }

	    public Book updateBook(Long id, Book updatedBook) {
	        return bookRepository.findById(id).map(book -> {
	            book.setTitle(updatedBook.getTitle());
	            book.setAuthor(updatedBook.getAuthor());
	            book.setGenre(updatedBook.getGenre());
	            book.setStock(updatedBook.getStock());
	            return bookRepository.save(book);
	        }).orElseThrow(() -> new RuntimeException("Book not found"));
	    }

}
