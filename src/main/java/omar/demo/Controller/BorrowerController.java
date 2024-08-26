package omar.demo.Controller;

import lombok.RequiredArgsConstructor;
import omar.demo.Exception.GeneralException;
import omar.demo.Exception.ValidationException;
import omar.demo.Exception.ErrorCode;
import omar.demo.Model.Borrower;
import omar.demo.Service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrowers")
@RequiredArgsConstructor
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    // Create a new borrower
    @PostMapping
    public ResponseEntity<Borrower> createBorrower(@RequestBody Borrower borrower) throws ValidationException {
        if (borrower.getName() == null || borrower.getName().isEmpty()) {
            throw new ValidationException("Borrower name is required");
        }
        Borrower createdBorrower = borrowerService.createBorrower(borrower);
        return ResponseEntity.ok(createdBorrower);
    }

    // Get all borrowers
    @GetMapping
    public ResponseEntity<List<Borrower>> getAllBorrowers() {
        List<Borrower> borrowers = borrowerService.getAllBorrowers();
        return ResponseEntity.ok(borrowers);
    }

    // Get a borrower by ID
    @GetMapping("/{id}")
    public ResponseEntity<Borrower> getBorrowerById(@PathVariable Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        Borrower borrower = borrowerService.getBorrowerById(id)
                .orElseThrow(() -> new GeneralException("Borrower not found with ID: " + id, ErrorCode.ID_NOT_FOUND));
        return ResponseEntity.ok(borrower);
    }

    // Update a borrower by ID
    @PutMapping("/{id}")
    public ResponseEntity<Borrower> updateBorrower(@PathVariable Long id, @RequestBody Borrower borrowerDetails) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (borrowerDetails.getName() == null || borrowerDetails.getName().isEmpty()) {
            throw new ValidationException("Borrower name is required");
        }
        Borrower updatedBorrower = borrowerService.updateBorrower(id, borrowerDetails);
        if (updatedBorrower != null) {
            return ResponseEntity.ok(updatedBorrower);
        } else {
            throw new GeneralException("Borrower not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
    }

    // Delete a borrower by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (!borrowerService.getBorrowerById(id).isPresent()) {
            throw new GeneralException("Borrower not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
        borrowerService.deleteBorrower(id);
        return ResponseEntity.noContent().build();
    }
}
