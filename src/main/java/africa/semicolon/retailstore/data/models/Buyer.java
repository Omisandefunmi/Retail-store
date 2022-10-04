package africa.semicolon.retailstore.data.models;

import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Buyer {
    public Buyer() {
        cart = new Cart();
        cart.setTotalPrice(0.00);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(length = 400)
    private String address;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Cart cart;
}
