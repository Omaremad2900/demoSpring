package omar.demo.Repository;

import omar.demo.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);
    @Query("SELECT b FROM Book b WHERE b.publishedDate > :date")
    List<Book> findBooksPublishedAfter(@Param("date") Date date);
    @Query("SELECT b FROM Book b JOIN b.author a WHERE a.name = :authorName")
    List<Book> findBooksByAuthorName(@Param("authorName") String authorName);
}