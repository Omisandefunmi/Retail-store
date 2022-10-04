package africa.semicolon.retailstore.services.cart;

import africa.semicolon.retailstore.data.models.*;
import africa.semicolon.retailstore.data.repositories.CartRepository;
import africa.semicolon.retailstore.dtos.requests.AddItemRequest;
import africa.semicolon.retailstore.dtos.requests.UpdateCartRequest;
import africa.semicolon.retailstore.dtos.responses.CartDto;
import africa.semicolon.retailstore.services.product.ProductService;
import africa.semicolon.retailstore.web.exceptions.ProductQuantityOutOfRange;
import africa.semicolon.retailstore.web.exceptions.RetailStoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private ProductService productService;
    private CartRepository cartRepository;


    public CartServiceImpl(ProductService productService, CartRepository cartRepository) {
        this.productService = productService;
        this.cartRepository = cartRepository;
    }


    @Override
    public CartDto addItemToCart(AddItemRequest request) throws RetailStoreException {
        Product product = productService.findProductById(Long.parseLong(request.getProductId()));
        if (product == null) {
            throw new RetailStoreException("Product "+request.getProductId()+" not found");
        }
        if(!quantityIsValid(product, request.getQuantity())){
            throw new ProductQuantityOutOfRange("Stock quantity exceeded");
        }

        Item item = new Item(product, request.getQuantity());
        Cart buyerCart = request.getBuyerCart();
        buyerCart.addItem(item);
        double price = sumPurchaseAmount(item);
        buyerCart.setTotalPrice(buyerCart.getTotalPrice() + price);

        Cart savedCart = cartRepository.save(buyerCart);
        return CartDto.builder()
                .items(savedCart.getItems())
                .totalPrice(savedCart.getTotalPrice())
                .build();
    }

    @Override
    public CartDto updateCartItem(UpdateCartRequest request) throws RetailStoreException {
        Cart cart = request.getCart();
        Item item = searchItem(cart, request.getItemId());

        if(item.getPurchaseQuantity() == 0){
           throw new RetailStoreException("Item's quantity is zero!!!");
        }

        if (request.getUpdateType() == UpdateType.DECREASE){
            item.setPurchaseQuantity(item.getPurchaseQuantity() - 1);
            cart.setTotalPrice(cart.getTotalPrice() - item.getProduct().getPrice());
        }
        else if (request.getUpdateType() == UpdateType.INCREASE) {
            item.setPurchaseQuantity(item.getPurchaseQuantity() + 1);
            cart.setTotalPrice(cart.getTotalPrice() + item.getProduct().getPrice());
        }

        Cart savedCart = cartRepository.save(cart);
        return CartDto.builder()
                .items(savedCart.getItems())
                .totalPrice(savedCart.getTotalPrice())
                .build();
    }

    private Item searchItem(Cart cart, Long itemId) throws RetailStoreException {
        return cart.getItems().stream().filter(item1 -> item1.getId().equals(itemId)).findFirst().orElseThrow(() -> new RetailStoreException("Item not found"));
    }

    private double sumPurchaseAmount(Item item) {
        return item.getProduct().getPrice() * item.getPurchaseQuantity();
    }

    private boolean quantityIsValid(Product product, int quantity){
        return quantity <= product.getQuantity();
    }


}





















