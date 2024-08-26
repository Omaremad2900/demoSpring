package omar.demo.Controller;

import lombok.RequiredArgsConstructor;
import omar.demo.Exception.GeneralException;
import omar.demo.Exception.ValidationException;
import omar.demo.Exception.ErrorCode;
import omar.demo.Model.BorrowRecord;
import omar.demo.Service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowRecords")
@RequiredArgsConstructor
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    // Create a new borrow record
    @PostMapping
    public ResponseEntity<BorrowRecord> createBorrowRecord(@RequestBody BorrowRecord borrowRecord) throws ValidationException {
        if (borrowRecord.getBorrower() == null || borrowRecord.getBook() == null) {
            throw new ValidationException("Both borrower and book must be provided");
        }
        BorrowRecord createdBorrowRecord = borrowRecordService.createBorrowRecord(borrowRecord);
        return ResponseEntity.ok(createdBorrowRecord);
    }

    // Get all borrow records
    @GetMapping
    public ResponseEntity<List<BorrowRecord>> getAllBorrowRecords() {
        List<BorrowRecord> borrowRecords = borrowRecordService.getAllBorrowRecords();
        return ResponseEntity.ok(borrowRecords);
    }

    // Get borrow records by borrower ID
    @GetMapping("/borrower/{borrowerId}")
    public ResponseEntity<List<BorrowRecord>> getBorrowRecordsByBorrower(@PathVariable Long borrowerId) throws ValidationException {
        if (borrowerId == null || borrowerId <= 0) {
            throw new ValidationException("Valid borrower ID is required");
        }
        List<BorrowRecord> borrowRecords = borrowRecordService.getBorrowRecordsByBorrower(borrowerId);
        return ResponseEntity.ok(borrowRecords);
    }

    // Get borrow records by book ID
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BorrowRecord>> getBorrowRecordsByBook(@PathVariable Long bookId) throws ValidationException {
        if (bookId == null || bookId <= 0) {
            throw new ValidationException("Valid book ID is required");
        }
        List<BorrowRecord> borrowRecords = borrowRecordService.getBorrowRecordsByBook(bookId);
        return ResponseEntity.ok(borrowRecords);
    }

    // Update a borrow record by ID
    @PutMapping("/{id}")
    public ResponseEntity<BorrowRecord> updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecord borrowRecord) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (borrowRecord.getBorrower() == null || borrowRecord.getBook() == null) {
            throw new ValidationException("Both borrower and book must be provided");
        }
        BorrowRecord updatedBorrowRecord = borrowRecordService.updateBorrowRecord(id, borrowRecord);
        if (updatedBorrowRecord != null) {
            return ResponseEntity.ok(updatedBorrowRecord);
        } else {
            throw new GeneralException("Borrow record not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
    }

    // Delete a borrow record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowRecord(@PathVariable Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (borrowRecordService.getBorrowRecordsByBorrower(id)==null) {
            throw new GeneralException("Borrow record not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
        borrowRecordService.deleteBorrowRecord(id);
        return ResponseEntity.noContent().build();
    }
}
