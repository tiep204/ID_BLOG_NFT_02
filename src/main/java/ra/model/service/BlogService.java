package ra.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.model.entity.Blog;
import ra.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface BlogService {
    List<Blog> findAll();

    Blog findByID(int blogID);

    Blog saveOrUpdate(Blog blog);

    void delete(int blogID);

    List<Blog> searchByTitle(String blogTitle);
    List<Blog> sortByDateBlog(String direction);


    Page<Blog> searchTitleAndSort(String blogTitle,Pageable pageable);

}
