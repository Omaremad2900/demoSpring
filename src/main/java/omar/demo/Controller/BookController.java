package omar.demo.Controller;

import lombok.RequiredArgsConstructor;
import omar.demo.Exception.GeneralException;
import omar.demo.Exception.ValidationException;
import omar.demo.Exception.ErrorCode;
import omar.demo.Model.Book;
import omar.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    // Create a new book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) throws ValidationException {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new ValidationException("Book title is required");
        }
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.ok(createdBook);
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new GeneralException("Book not found with ID: " + id, ErrorCode.ID_NOT_FOUND));
        return ResponseEntity.ok(book);
    }

    // Get books by author ID
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable Long authorId) throws ValidationException {
        if (authorId == null || authorId <= 0) {
            throw new ValidationException("Valid author ID is required");
        }
        List<Book> books = bookService.getBooksByAuthor(authorId);
        return ResponseEntity.ok(books);
    }

    // Update a book by ID
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (bookDetails.getTitle() == null || bookDetails.getTitle().isEmpty()) {
            throw new ValidationException("Book title is required");
        }
        Book updatedBook = bookService.updateBook(id, bookDetails);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            throw new GeneralException("Book not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (!bookService.getBookById(id).isPresent()) {
            throw new GeneralException("Book not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
