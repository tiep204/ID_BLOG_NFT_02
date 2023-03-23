package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.model.entity.Blog;
import ra.model.repository.BlogRepository;
import ra.model.service.BlogService;

import java.util.List;

@Service
public class BlogServiceImp implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findByID(int blogID) {
        return blogRepository.findById(blogID).get();
    }

    @Override
    public Blog saveOrUpdate(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void delete(int blogID) {
        blogRepository.deleteById(blogID);
    }

    @Override
    public List<Blog> searchByTitle(String blogTitle) {
        return blogRepository.findByBlogTitleContaining(blogTitle);
    }

    @Override
    public List<Blog> sortByDateBlog(String direction) {
        if(direction.equals("asc")){
            return blogRepository.findAll(Sort.by("blogCreateDate").ascending());
        }else {
            return blogRepository.findAll(Sort.by("blogCreateDate").descending());
        }
    }



//    @Override
//    public List<Blog> searchTitleAndSort(String direction, String blogTitle) {
//        if(direction.equals("asc")){
//            return blogRepository.findAll(Sort.by("blogTitle").ascending());
//        }else {
//            return blogRepository.findAll(Sort.by("blogTitle").descending());
//        }
//    }

    @Override
    public Page<Blog> searchTitleAndSort(String blogTitle, Pageable pageable) {
        return blogRepository.findByBlogTitleContaining(blogTitle,pageable);
    }


}
