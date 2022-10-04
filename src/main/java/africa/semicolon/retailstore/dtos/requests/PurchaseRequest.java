package africa.semicolon.retailstore.dtos.requests;

import lombok.Getter;

@Getter
public class PurchaseRequest {
    private String buyerId;
    private String productId;
    private int quantity;

}
