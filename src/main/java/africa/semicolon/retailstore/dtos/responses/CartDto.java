package africa.semicolon.retailstore.dtos.responses;

import africa.semicolon.retailstore.data.models.Item;
import lombok.*;

import java.util.List;

@Setter
@AllArgsConstructor
@Builder
@Getter
public class CartDto {
    private List<Item> items;
    private double totalPrice;
}
