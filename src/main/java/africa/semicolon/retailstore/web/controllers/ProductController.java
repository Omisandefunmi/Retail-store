package africa.semicolon.retailstore.web.controllers;

import africa.semicolon.retailstore.data.models.Product;
import africa.semicolon.retailstore.dtos.requests.CreateProductRequest;
import africa.semicolon.retailstore.dtos.responses.ProductDto;
import africa.semicolon.retailstore.services.product.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createProduct(@ModelAttribute CreateProductRequest createProductRequest) throws IOException {
            ProductDto apiResponse = productService.createProduct(createProductRequest);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
            ProductDto apiResponse = productService.updateProductDetails(id, patch);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllProducts(){
        List<Product> allProducts = productService.findAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
}
