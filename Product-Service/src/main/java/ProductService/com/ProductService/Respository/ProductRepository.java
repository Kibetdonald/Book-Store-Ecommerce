package ProductService.com.ProductService.Respository;

import ProductService.com.ProductService.Models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
