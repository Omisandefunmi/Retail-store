package africa.semicolon.retailstore.dtos.requests;

import africa.semicolon.retailstore.data.models.Cart;
import africa.semicolon.retailstore.data.models.UpdateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateCartRequest {

    private Cart cart;
    private Long itemId;
    private UpdateType updateType;
}
