package omar.demo.Repository;

import omar.demo.Model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    // Find a borrower by email
    Optional<Borrower> findByEmail(String email);

    // Check if a borrower exists by email
    boolean existsByEmail(String email);
}
