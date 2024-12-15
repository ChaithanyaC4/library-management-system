package com.library.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.lms.entity.Book;
import com.library.lms.entity.User;
import com.library.lms.service.BookService;
import com.library.lms.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

	 @Autowired
	    private BookService bookService;
	 @Autowired
	    private UserService userService;
	 @GetMapping("/profile")
	    public User getReaderProfile(@RequestParam Long userId) { // Highlighted: Fetch user profile
	        return userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	    }

	  @PostMapping("/books/borrow") 
	    public String borrowBook(@RequestParam Long userId, @RequestParam Long bookId) { // Highlighted: Borrow a book
	        Optional<Book> book = bookService.getBookById(bookId);
	        if (book.isPresent()) {
	            Book borrowedBook = book.get();
	            if (borrowedBook.getStock() > 0) {
	                // Fetch user and add book to borrowed list
	                User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	                user.getBorrowedBooks().add(borrowedBook.getTitle());
	                
	                // Update the user with the new borrowed list
	                userService.updateUser(userId, user);
	                
	                // Decrease book stock
	                borrowedBook.setStock(borrowedBook.getStock() - 1);
	                bookService.updateBook(borrowedBook.getId(), borrowedBook);
	                
	                return "Book borrowed successfully!";
	            } else {
	                return "Book out of stock.";
	            }
	        }
	        return "Book not found.";
	    }

	    @PostMapping("/books/return") 
	    public String returnBook(@RequestParam Long userId, @RequestParam Long bookId) { // Highlighted: Return a borrowed book
	        Optional<Book> book = bookService.getBookById(bookId);
	        if (book.isPresent()) {
	            // Fetch user and remove book from borrowed list
	            User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	            user.getBorrowedBooks().remove(book.get().getTitle());
	            
	            // Update the user with the new borrowed list
	            userService.updateUser(userId, user);
	            
	            // Increase book stock
	            book.get().setStock(book.get().getStock() + 1);
	            bookService.updateBook(book.get().getId(), book.get());
	            
	            return "Book returned successfully!";
	        }
	        return "Book not found.";
	    }
	

	    @GetMapping("/books/{id}")
	    public Optional<Book> getBookDetails(@PathVariable Long id) { // Highlighted: Get book details
	        return bookService.getBookById(id);
	    }

	    @PostMapping("/create")
	    public Book createBook(@RequestBody Book book) {
	        return bookService.createBook(book);
	    }

	    @GetMapping("/{id}")
	    public Optional<Book> getBook(@PathVariable Long id) {
	        return bookService.getBookById(id);
	    }

	    @DeleteMapping("/delete/{id}")
	    public void deleteBook(@PathVariable Long id) {
	        bookService.deleteBook(id);
	    }

	    @GetMapping
	    public List<Book> getAllBooks() {
	        return bookService.getAllBooks();
	    }

	    @GetMapping("/search")
	    public List<Book> searchBooks(@RequestParam String keyword) {
	        return bookService.searchBooks(keyword);
	    }
	    @GetMapping("/author/{author}") // Highlighted
	    public List<Book> getBooksByAuthor(@PathVariable String author) {
	        return bookService.getBooksByAuthor(author);
	    }

	    @PutMapping("/update/{id}")
	    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
	        return bookService.updateBook(id, book);
	        
	        
	    }
}
