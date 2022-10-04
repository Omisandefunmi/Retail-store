package africa.semicolon.retailstore.dtos.responses;

import africa.semicolon.retailstore.data.models.Cart;
import africa.semicolon.retailstore.data.models.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class PurchaseResponse {
    private List<Item> items;
    private double totalPrice;
}
