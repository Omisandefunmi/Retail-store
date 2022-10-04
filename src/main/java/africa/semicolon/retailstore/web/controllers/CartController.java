package africa.semicolon.retailstore.web.controllers;

import africa.semicolon.retailstore.dtos.requests.AddItemRequest;
import africa.semicolon.retailstore.dtos.requests.UpdateCartRequest;
import africa.semicolon.retailstore.dtos.responses.CartDto;
import africa.semicolon.retailstore.services.cart.CartService;
import africa.semicolon.retailstore.web.exceptions.RetailStoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {
    private CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<?> addItemToCart(AddItemRequest addItemRequest){
        try{
            CartDto apiResponse = cartService.addItemToCart(addItemRequest);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (RetailStoreException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/updateCart")
    public ResponseEntity<?> updateCartItems(UpdateCartRequest updateCartRequest){
        try {
            CartDto apiResponse = cartService.updateCartItem(updateCartRequest);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (RetailStoreException e) {
            throw new RuntimeException(e);
        }
    }

}
