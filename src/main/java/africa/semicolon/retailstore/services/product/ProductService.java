package africa.semicolon.retailstore.services.product;

import africa.semicolon.retailstore.data.models.Product;
import africa.semicolon.retailstore.dtos.requests.CreateProductRequest;
import africa.semicolon.retailstore.dtos.responses.ProductDto;
import africa.semicolon.retailstore.web.exceptions.RetailStoreException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product findProductById(Long productId);
    List<Product> findAllProducts();
    ProductDto createProduct(CreateProductRequest createProductResponse) throws RetailStoreException, IOException;
    ProductDto updateProductDetails(Long productId, JsonPatch productPatch) throws RetailStoreException, JsonPatchException, JsonProcessingException;
    String deleteProduct(String productId) throws RetailStoreException;
}
