package africa.semicolon.retailstore.dtos.requests;

import africa.semicolon.retailstore.data.models.Cart;
import africa.semicolon.retailstore.data.models.UpdateType;
import lombok.Getter;

@Getter
public class UpdateRequest {
    private String buyerId;
    private Long itemId;
    private UpdateType updateType;
}
