package africa.semicolon.retailstore.data.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;
    @CreationTimestamp
    private LocalDateTime purchaseTime;
    private double totalPrice;

    public void addItem(Item item){
        if(items == null){
            items = new ArrayList<>();
        }
        items.add(item);
    }
}
