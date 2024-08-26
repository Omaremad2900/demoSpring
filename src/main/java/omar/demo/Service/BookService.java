package omar.demo.Service;

import lombok.extern.slf4j.Slf4j;
import omar.demo.Exception.GeneralException;
import omar.demo.Exception.ValidationException;
import omar.demo.Exception.ErrorCode;
import omar.demo.Model.Book;
import omar.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) throws ValidationException {
        if (book.getTitle().isEmpty()) {
            throw new ValidationException("Book title is required");
        }
        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new ValidationException("Author must be provided and have a valid ID.");
        }
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) throws ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        return bookRepository.findById(id);
    }

    public List<Book> getBooksByAuthor(Long authorId) throws ValidationException {
        if (authorId == null || authorId <= 0) {
            throw new ValidationException("Valid author ID is required");
        }
        return bookRepository.findByAuthorId(authorId);
    }

    public Book updateBook(Long id, Book bookDetails) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (bookDetails.getTitle() == null || bookDetails.getTitle().isEmpty()) {
            throw new ValidationException("Book title is required");
        }
        if (!bookRepository.existsById(id)) {
            throw new GeneralException("Book not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
        bookDetails.setId(id);
        return bookRepository.save(bookDetails);
    }

    public void deleteBook(Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (!bookRepository.existsById(id)) {
            throw new GeneralException("Book not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
        bookRepository.deleteById(id);
    }
}

