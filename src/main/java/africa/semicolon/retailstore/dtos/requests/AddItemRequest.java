package africa.semicolon.retailstore.dtos.requests;

import africa.semicolon.retailstore.data.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class AddItemRequest {
    private Cart buyerCart;
    private String productId;
    private int quantity;
}
