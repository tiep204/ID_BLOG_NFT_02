package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Blog;
import ra.model.entity.Comment;
import ra.model.entity.Tag;
import ra.model.entity.User;
import ra.model.service.BlogService;
import ra.model.service.UserService;
import ra.payload.request.BlogRequest;
import ra.security.CustomUserDetail;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;


    ////getAllblog////

    @GetMapping("/getAllBlog")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Blog> getAllBlog(){
        return blogService.findAll();

    }
    ///end getallBlog////

    ////start getBlogByID///
    @GetMapping("/{blogID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Blog findByIDBlog(@PathVariable("blogID") int blogID){
        return blogService.findByID(blogID);
    }
    ///end GetBlogById///

    //start createBlog///
    @PostMapping("/createBlog")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createBlog(@RequestBody BlogRequest blog){
        try {
            CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User users = userService.findByUserId(userDetails.getUserId());
            Blog blogs = new Blog();
            blogs.setUser(users);
            blogs.setBlogTitle(blog.getBlogTitle());
            blogs.setBlogContent(blog.getBlogContent());
            blogs.setBlogImage(blog.getBlogImage());
            blogs.setBlogCreateDate(java.time.LocalDate.now());
            blogs.setBlogStatus(true);
             blogService.saveOrUpdate(blogs);
//            for (Tag ta: blog.getListTag()) {
//                Tag tag = new Tag();
//                tag.setTagName(ta.getTagName());
//                tag.setTagStatus(true);
//            }
            return ResponseEntity.ok("Bạn đã thêm mới thành công blog");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("bạn chưa thêm mới thành công blog😁😁😁");
        }
    }
    //end createBlog///
//start upsate blog///
    @PutMapping("/updateBlog/{blogID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateBlog(@PathVariable("blogID") int blogID,@RequestBody BlogRequest blog){
        try {
            CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User users = userService.findByUserId(userDetails.getUserId());
            Blog blogUpdate = blogService.findByID(blogID);
            blogUpdate.setUser(users);
            blogUpdate.setBlogTitle(blog.getBlogTitle());
            blogUpdate.setBlogContent(blog.getBlogContent());
            blogUpdate.setBlogImage(blog.getBlogImage());
            blogUpdate.setBlogCreateDate(java.time.LocalDate.now());
            blogUpdate.setBlogStatus(true);
            blogService.saveOrUpdate(blogUpdate);
            return ResponseEntity.ok("bạn đã cập nhật thành công blog của bạn");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("cập nhật không thành công");
        }
    }

    //end updateblog///

    ////start deleteBlog////
    @DeleteMapping("/delete/{blogID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteComment(@PathVariable("blogID") int blogID) {
        try {
            blogService.findByID(blogID);
            blogService.delete(blogID);
            return ResponseEntity.ok("Bạn đã xóa comment thành công 😘😘😘");
        } catch (Exception e) {
            return ResponseEntity.ok("Đã có vấn đề trong quá trình xóa");
        }
    }
    ///end deleteBlog////

    //start searchTitleBlog///

    @GetMapping("/searchTitleBlog")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Blog> searchTitleBlog(@RequestParam("blogTitle") String blogTitle){
        return blogService.searchByTitle(blogTitle);

    }
    //end searchTitleBlog///

    ///start sortTimeBlog///
    @GetMapping("/sortByDaTimeBlog")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Blog>> sortByDateCreate(@RequestParam("direction") String direction){
        List<Blog> listBlog = blogService.sortByDateBlog(direction);
        return new ResponseEntity<>(listBlog, HttpStatus.OK);
    }
    //end sortTimeBlog///



}
