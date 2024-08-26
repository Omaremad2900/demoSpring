package omar.demo.Repository;

import omar.demo.Model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByBorrowerId(Long borrowerId);
    List<BorrowRecord> findByBookId(Long bookId);
}

