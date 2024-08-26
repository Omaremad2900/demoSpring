package omar.demo.Controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import omar.demo.Exception.GeneralException;
import omar.demo.Exception.ValidationException;
import omar.demo.Exception.ErrorCode;
import omar.demo.Model.Author;
import omar.demo.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
@Log4j2
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // Create a new author
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) throws ValidationException, GeneralException {
        if (author.getName() == null || author.getName().isEmpty()) {
            throw new ValidationException("Author name is required");
        }
        Author createdAuthor = authorService.createAuthor(author);
        return ResponseEntity.ok(createdAuthor);
    }

    // Get all authors
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    // Get an author by ID
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        Author author = authorService.getAuthorById(id)
                .orElseThrow(() -> new GeneralException("Author not found with ID: " + id, ErrorCode.Author_NOT_FOUND));
        return ResponseEntity.ok(author);
    }

    // Update an author by ID
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (authorDetails.getName() == null || authorDetails.getName().isEmpty()) {
            throw new ValidationException("Author name is required");
        }
        Author updatedAuthor = authorService.updateAuthor(id, authorDetails);
        return ResponseEntity.ok(updatedAuthor);
    }

    // Delete an author by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
