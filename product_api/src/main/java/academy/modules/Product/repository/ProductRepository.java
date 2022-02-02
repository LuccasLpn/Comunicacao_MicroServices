package academy.modules.Product.repository;

import academy.modules.Product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {

}
