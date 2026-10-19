package com.example.productapi;

import com.example.productapi.dto.ProductRequest;
import com.example.productapi.dto.ProductResponse;
import com.example.productapi.exception.ProductNotFoundException;
import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import com.example.productapi.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductRequest request;

    @BeforeEach
    void setUp() {
        product = new Product("Widget", "A useful widget", new BigDecimal("9.99"), 100);

        request = new ProductRequest();
        request.setName("Widget");
        request.setDescription("A useful widget");
        request.setPrice(new BigDecimal("9.99"));
        request.setStockQuantity(100);
    }

    @Test
    void getAllProducts_returnsAll() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        List<ProductResponse> result = productService.getAllProducts();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Widget");
    }

    @Test
    void getProductById_found() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductResponse result = productService.getProductById(1L);
        assertThat(result.getName()).isEqualTo("Widget");
    }

    @Test
    void getProductById_notFound_throws() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> productService.getProductById(99L))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void createProduct_savesAndReturns() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        ProductResponse result = productService.createProduct(request);
        assertThat(result.getName()).isEqualTo("Widget");
        assertThat(result.getPrice()).isEqualByComparingTo("9.99");
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void deleteProduct_notFound_throws() {
        when(productRepository.existsById(99L)).thenReturn(false);
        assertThatThrownBy(() -> productService.deleteProduct(99L))
                .isInstanceOf(ProductNotFoundException.class);
    }
}
