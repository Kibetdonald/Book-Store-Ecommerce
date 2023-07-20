package ProductService.com.ProductService.Controller;


import ProductService.com.ProductService.Controllers.ProductController;
import ProductService.com.ProductService.Models.CategoryModel;
import ProductService.com.ProductService.Models.ProductModel;
import ProductService.com.ProductService.Services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private List<ProductModel> testProducts;

    @BeforeEach
    void setUp() {
        testProducts = new ArrayList<>();
        testProducts.add(new ProductModel(1L, "Product 1", "Description 1", 10.0, CategoryModel.Fictional));
        testProducts.add(new ProductModel(2L, "Product 2", "Description 2", 20.0, CategoryModel.NonFictional));
    }

    @Test
    void testGetAllProducts() {
        when(productService.getAllProducts()).thenReturn(testProducts);

        ResponseEntity<List<ProductModel>> responseEntity = productController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testProducts, responseEntity.getBody());
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        ProductModel product = testProducts.get(0);

        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<ProductModel> responseEntity = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    void testGetProductById_NotFound() {
        Long productId = 3L;

        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        ResponseEntity<ProductModel> responseEntity = productController.getProductById(productId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testCreateProduct() {
        ProductModel newProduct = new ProductModel(null, "New Product", "New Description", 30.0, CategoryModel.Fictional);
        ProductModel createdProduct = new ProductModel(3L, "New Product", "New Description", 30.0, CategoryModel.NonFictional);

        when(productService.createProduct(newProduct)).thenReturn(createdProduct);

        ResponseEntity<ProductModel> responseEntity = productController.createProduct(newProduct);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdProduct, responseEntity.getBody());
    }

    @Test
    void testDeleteProduct() {
        Long productId = 2L;

        ResponseEntity<Void> responseEntity = productController.deleteProduct(productId);

        verify(productService, times(1)).deleteProduct(productId);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
