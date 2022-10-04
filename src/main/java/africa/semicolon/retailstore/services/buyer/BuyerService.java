package africa.semicolon.retailstore.services.buyer;

import africa.semicolon.retailstore.dtos.requests.*;
import africa.semicolon.retailstore.dtos.responses.LogInResponse;
import africa.semicolon.retailstore.dtos.responses.PurchaseResponse;
import africa.semicolon.retailstore.dtos.responses.SignUpResponse;
import africa.semicolon.retailstore.web.exceptions.BuyerEmailAlreadyExistException;
import africa.semicolon.retailstore.web.exceptions.BuyerEmailDoesNotExist;
import africa.semicolon.retailstore.web.exceptions.IncorrectLogInException;
import africa.semicolon.retailstore.web.exceptions.RetailStoreException;

public interface BuyerService {
    SignUpResponse signUp(SignUpRequest request) throws BuyerEmailAlreadyExistException;

    LogInResponse logIn(LogInRequest logInRequest) throws BuyerEmailDoesNotExist, IncorrectLogInException;

    PurchaseResponse makePurchase(PurchaseRequest purchaseRequest) throws RetailStoreException;

    PurchaseResponse updateCart(UpdateRequest updateRequest) throws RetailStoreException;
}
