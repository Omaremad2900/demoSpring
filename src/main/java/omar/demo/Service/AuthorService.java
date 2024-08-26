package omar.demo.Service;

import lombok.extern.slf4j.Slf4j;
import omar.demo.Exception.GeneralException;
import omar.demo.Exception.ValidationException;
import omar.demo.Exception.ErrorCode;
import omar.demo.Model.Author;
import omar.demo.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(Author author) throws ValidationException, GeneralException {
        if (author.getName() == null || author.getName().isEmpty()) {
            throw new ValidationException("Author name is required");
        }
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        return authorRepository.findById(id);
    }

    public Author updateAuthor(Long id, Author authorDetails) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Author not found with ID: " + id, ErrorCode.Author_NOT_FOUND));

        if (authorDetails.getName() == null || authorDetails.getName().isEmpty()) {
            throw new ValidationException("Author name is required");
        }

        author.setName(authorDetails.getName());
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Author not found with ID: " + id, ErrorCode.Author_NOT_FOUND));
        authorRepository.delete(author);
    }
}
