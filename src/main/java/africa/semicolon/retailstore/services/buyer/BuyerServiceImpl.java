package africa.semicolon.retailstore.services.buyer;

import africa.semicolon.retailstore.data.models.Buyer;
import africa.semicolon.retailstore.data.models.Cart;
import africa.semicolon.retailstore.data.repositories.BuyerRepository;
import africa.semicolon.retailstore.dtos.requests.*;
import africa.semicolon.retailstore.dtos.responses.CartDto;
import africa.semicolon.retailstore.dtos.responses.LogInResponse;
import africa.semicolon.retailstore.dtos.responses.PurchaseResponse;
import africa.semicolon.retailstore.dtos.responses.SignUpResponse;
import africa.semicolon.retailstore.services.cart.CartService;
import africa.semicolon.retailstore.web.exceptions.BuyerEmailAlreadyExistException;
import africa.semicolon.retailstore.web.exceptions.BuyerEmailDoesNotExist;
import africa.semicolon.retailstore.web.exceptions.IncorrectLogInException;
import africa.semicolon.retailstore.web.exceptions.RetailStoreException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    private BuyerRepository buyerRepository;
    private CartService cartService;
    private ModelMapper modelMapper;

    public BuyerServiceImpl(BuyerRepository buyerRepository, ModelMapper modelMapper, CartService cartService) {
        this.buyerRepository = buyerRepository;
        this.modelMapper = modelMapper;
        this.cartService = cartService;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) throws BuyerEmailAlreadyExistException {
       Optional <Buyer> existingBuyer = buyerRepository.findBuyerByEmail(signUpRequest.getEmail());
       if(existingBuyer.isPresent()){
           throw new BuyerEmailAlreadyExistException(signUpRequest.getEmail()+" already exists!");
       }
       Buyer buyer = Buyer.builder()
               .firstName(signUpRequest.getFirstName())
               .lastName(signUpRequest.getLastName())
               .address(signUpRequest.getAddress())
               .email(signUpRequest.getEmail())
               .password(signUpRequest.getPassword())
               .build();
         Buyer savedBuyer = buyerRepository.save(buyer);
        return modelMapper.map(savedBuyer, SignUpResponse.class);
    }

    @Override
    public LogInResponse logIn(LogInRequest logInRequest) throws BuyerEmailDoesNotExist, IncorrectLogInException {
        Buyer existingBuyer = buyerRepository.findBuyerByEmail(logInRequest.getEmail()).orElseThrow(()-> new BuyerEmailDoesNotExist(logInRequest.getEmail()+" does not exist"));
        verifyLogInDetails(logInRequest, existingBuyer);
        LogInResponse response =  modelMapper.map(existingBuyer, LogInResponse.class);
        response.setLogin(true);
        return response;
    }

    @Override
    public PurchaseResponse makePurchase(PurchaseRequest purchaseRequest) throws RetailStoreException {
        Buyer buyer = getBuyerFromRepository(purchaseRequest.getBuyerId());
        AddItemRequest cartServiceRequest = AddItemRequest.builder()
                .buyerCart(buyer.getCart())
                .productId(purchaseRequest.getProductId())
                .quantity(purchaseRequest.getQuantity())
                .build();

        CartDto loadedCart = cartService.addItemToCart(cartServiceRequest);
        Cart cart = Cart.builder()
                .items(loadedCart.getItems())
                .totalPrice(loadedCart.getTotalPrice())
                .build();
        cart.setPurchaseTime(LocalDateTime.now());
        buyer.setCart(cart);
        buyerRepository.save(buyer);

        return PurchaseResponse.builder()
                .items(cart.getItems()).totalPrice(cart.getTotalPrice()).build();
    }

    @Override
    public PurchaseResponse updateCart(UpdateRequest updateRequest) throws RetailStoreException {
        Buyer buyer = getBuyerFromRepository(updateRequest.getBuyerId());
        UpdateCartRequest updateCartRequest = UpdateCartRequest.builder()
                .cart(buyer.getCart())
                .updateType(updateRequest.getUpdateType())
                .itemId(updateRequest.getItemId())
                .build();

        CartDto updatedCart = cartService.updateCartItem(updateCartRequest);
        Cart cart = Cart.builder()
                .items(updatedCart.getItems())
                .totalPrice(updatedCart.getTotalPrice())
                .build();
        buyer.setCart(cart);
        buyerRepository.save(buyer);

        return PurchaseResponse.builder()
                .items(cart.getItems()).totalPrice(cart.getTotalPrice()).build();

    }


    private Buyer getBuyerFromRepository(String updateRequest) throws RetailStoreException {
        Optional<Buyer> found = buyerRepository.findById(Long.parseLong(updateRequest));
        if (found.isEmpty()) {
            throw new RetailStoreException("Buyer Not Found Exception");
        }
        return found.get();
    }

    private void verifyLogInDetails(LogInRequest logInRequest, Buyer existingBuyer) throws IncorrectLogInException {
        if(!existingBuyer.getPassword().equals(logInRequest.getPassword())){
            throw new IncorrectLogInException("Incorrect Log Details");
        }
    }

}
