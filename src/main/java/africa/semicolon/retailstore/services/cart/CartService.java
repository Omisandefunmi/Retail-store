package africa.semicolon.retailstore.services.cart;

import africa.semicolon.retailstore.data.models.Cart;
import africa.semicolon.retailstore.dtos.requests.AddItemRequest;
import africa.semicolon.retailstore.dtos.requests.UpdateCartRequest;
import africa.semicolon.retailstore.dtos.responses.CartDto;
import africa.semicolon.retailstore.web.exceptions.RetailStoreException;

public interface CartService {
    CartDto addItemToCart(AddItemRequest request) throws RetailStoreException;
    CartDto updateCartItem(UpdateCartRequest request) throws RetailStoreException;


}
