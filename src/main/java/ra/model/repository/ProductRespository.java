package ra.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Blog;
import ra.model.entity.Product;

import java.util.List;

@Repository
public interface ProductRespository extends JpaRepository<Product,Integer> {

    List<Product> findByProductNameContaining(String productName);
    Page<Product> findByProductNameContaining(String ProductName, Pageable pageable);


}
