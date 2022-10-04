package africa.semicolon.retailstore.data.repositories;

import africa.semicolon.retailstore.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long> {
   Optional<Product> findByProductName(String name);
}
