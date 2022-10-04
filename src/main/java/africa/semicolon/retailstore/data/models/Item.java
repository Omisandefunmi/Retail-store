package africa.semicolon.retailstore.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;
    private Integer purchaseQuantity;

    public Item(Product product, int purchaseQuantity){
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }
}
