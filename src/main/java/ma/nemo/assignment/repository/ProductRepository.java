package ma.nemo.assignment.repository;

import ma.nemo.assignment.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Product findByProductCode(String productCode);
  @Query("SELECT p FROM Product p Where p.quantityInStock < p.quantityThreshold ")
  List<Product> findProductsBelowThreshold();

 }