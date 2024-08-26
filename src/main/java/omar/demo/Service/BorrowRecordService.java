package omar.demo.Service;

import lombok.extern.slf4j.Slf4j;
import omar.demo.Exception.GeneralException;
import omar.demo.Exception.ValidationException;
import omar.demo.Exception.ErrorCode;
import omar.demo.Model.BorrowRecord;
import omar.demo.Repository.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BorrowRecordService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    public BorrowRecord createBorrowRecord(BorrowRecord borrowRecord) throws ValidationException {
        if (borrowRecord.getBorrower() == null || borrowRecord.getBook() == null) {
            throw new ValidationException("Both borrower and book must be provided");
        }
        return borrowRecordRepository.save(borrowRecord);
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    public List<BorrowRecord> getBorrowRecordsByBorrower(Long borrowerId) throws ValidationException {
        if (borrowerId == null || borrowerId <= 0) {
            throw new ValidationException("Valid borrower ID is required");
        }
        return borrowRecordRepository.findByBorrowerId(borrowerId);
    }

    public List<BorrowRecord> getBorrowRecordsByBook(Long bookId) throws ValidationException {
        if (bookId == null || bookId <= 0) {
            throw new ValidationException("Valid book ID is required");
        }
        return borrowRecordRepository.findByBookId(bookId);
    }

    public Optional<BorrowRecord> getBorrowRecordById(Long id) throws ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        return borrowRecordRepository.findById(id);
    }

    public BorrowRecord updateBorrowRecord(Long id, BorrowRecord borrowRecord) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (borrowRecord.getBorrower() == null || borrowRecord.getBook() == null) {
            throw new ValidationException("Both borrower and book must be provided");
        }
        if (!borrowRecordRepository.existsById(id)) {
            throw new GeneralException("Borrow record not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
        borrowRecord.setId(id);
        return borrowRecordRepository.save(borrowRecord);
    }

    public void deleteBorrowRecord(Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (!borrowRecordRepository.existsById(id)) {
            throw new GeneralException("Borrow record not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
        borrowRecordRepository.deleteById(id);
    }
}
