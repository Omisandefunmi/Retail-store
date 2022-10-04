package africa.semicolon.retailstore.data.repositories;

import africa.semicolon.retailstore.data.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
