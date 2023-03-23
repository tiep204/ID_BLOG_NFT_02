package ra.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.model.entity.Blog;
import ra.model.entity.Product;
import ra.payload.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int productId);
    Product saveOfUpdate(Product product);
    void delete(int productId);
    List<Product> searchByName(String productName);
    List<Product> sortByProductPrice(String direction);
    Page<Product> sortByNameAndPagination(Pageable pageable);
    Page<Product> pagging(Pageable pageable);
    Page<Product> searchProductNameAndSort(String ProductName, Pageable pageable);

}
