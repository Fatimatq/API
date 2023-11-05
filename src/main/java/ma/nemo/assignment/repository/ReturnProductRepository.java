package ma.nemo.assignment.repository;

import ma.nemo.assignment.domain.ReturnProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReturnProductRepository extends JpaRepository<ReturnProduct, Long> {
}
