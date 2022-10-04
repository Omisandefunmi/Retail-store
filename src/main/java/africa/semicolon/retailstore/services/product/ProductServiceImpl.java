package africa.semicolon.retailstore.services.product;

import africa.semicolon.retailstore.data.models.Product;
import africa.semicolon.retailstore.data.repositories.ProductRepository;
import africa.semicolon.retailstore.dtos.requests.CreateProductRequest;
import africa.semicolon.retailstore.dtos.responses.ProductDto;
import africa.semicolon.retailstore.services.cloud.CloudService;
import africa.semicolon.retailstore.web.exceptions.ProductDoesNotExistException;
import africa.semicolon.retailstore.web.exceptions.RetailStoreException;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private CloudService cloudService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CloudService cloudService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.cloudService = cloudService;
    }



    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto createProduct(CreateProductRequest createProductRequest) throws RetailStoreException, IOException {
        Optional<Product> optionalProduct = productRepository.findByProductName(createProductRequest.getName());
        if(optionalProduct.isPresent()) {
            throw new RetailStoreException("Product already exists");
        }

        Product product = new Product();
        if(createProductRequest.getImage() != null){
            Map<?,?> uploadResponse = cloudService.upload(createProductRequest.getImage().getBytes(), ObjectUtils.asMap("public_id", "catalogue/" +createProductRequest.getImage().getOriginalFilename(),
                    "overwrite", true));

            product.setImageUrl(uploadResponse.get("url").toString());
        }

        product.setProductName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setPrice(createProductRequest.getPrice());
        product.setQuantity(createProductRequest.getQuantity());

        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProductDetails(Long productId, JsonPatch productPatch) throws RetailStoreException, JsonPatchException, JsonProcessingException {
       if(productId == null){throw new RetailStoreException("Product id can not be null");}
        Optional <Product> found = productRepository.findById(productId);
       if(found.isEmpty()){
           throw new ProductDoesNotExistException("Product not found!");
       }

       Product productToPatch =  found.get();
       productToPatch = applyUpdateToProduct(productPatch, productToPatch);
       Product patchedProduct = productRepository.save(productToPatch);
        return modelMapper.map(patchedProduct, ProductDto.class);
    }

    @Override
    public String deleteProduct(String productId) throws RetailStoreException {
        Product deletedProduct = productRepository.findById(Long.parseLong(productId)).orElseThrow(() -> new RetailStoreException("Product not found"));
            productRepository.delete(deletedProduct);
        return deletedProduct.getProductName()+" has been deleted successfully";
    }

    private Product applyUpdateToProduct(JsonPatch productPatch, Product productToPatch) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = productPatch.apply(objectMapper.convertValue(productToPatch, JsonNode.class));

        return objectMapper.treeToValue(patched, Product.class);
    }


}
