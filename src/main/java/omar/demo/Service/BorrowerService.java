package omar.demo.Service;

import lombok.extern.slf4j.Slf4j;
import omar.demo.Exception.GeneralException;
import omar.demo.Exception.ValidationException;
import omar.demo.Exception.ErrorCode;
import omar.demo.Model.Borrower;
import omar.demo.Repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;

    public Borrower createBorrower(Borrower borrower) throws ValidationException {
        if (borrower.getName() == null || borrower.getName().isEmpty()) {
            throw new ValidationException("Borrower name is required");
        }
        return borrowerRepository.save(borrower);
    }

    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }

    public Optional<Borrower> getBorrowerById(Long id) throws ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        return borrowerRepository.findById(id);
    }

    public Borrower updateBorrower(Long id, Borrower borrowerDetails) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (borrowerDetails.getName() == null || borrowerDetails.getName().isEmpty()) {
            throw new ValidationException("Borrower name is required");
        }
        if (!borrowerRepository.existsById(id)) {
            throw new GeneralException("Borrower not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
        borrowerDetails.setId(id);
        return borrowerRepository.save(borrowerDetails);
    }

    public void deleteBorrower(Long id) throws GeneralException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Valid ID is required");
        }
        if (!borrowerRepository.existsById(id)) {
            throw new GeneralException("Borrower not found with ID: " + id, ErrorCode.ID_NOT_FOUND);
        }
        borrowerRepository.deleteById(id);
    }
}
