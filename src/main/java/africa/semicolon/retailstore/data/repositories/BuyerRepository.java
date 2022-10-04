package africa.semicolon.retailstore.data.repositories;

import africa.semicolon.retailstore.data.models.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Optional<Buyer> findBuyerByEmail(String email);
}

