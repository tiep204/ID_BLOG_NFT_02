package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import ra.model.service.TagService;
import ra.model.service.UserService;
import ra.payload.request.BlogRequest;
import ra.payload.response.BlogResponse;
import ra.security.CustomUserDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;


    ////getAllblog////

//    @GetMapping("/getAllBlog")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public List<Blog> getAllBlog() {
//        return blogService.findAll();
//
//    }

    @GetMapping("/getAllBlog")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<BlogResponse> getAllBlog() {
        List<BlogResponse> listBlogRe = new ArrayList<>();
        List<Blog> blogList = blogService.findAll();
        for (Blog blog: blogList) {
            BlogResponse bl = new BlogResponse();
            bl.setBlogID(blog.getBlogID());
            bl.setBlogTitle(blog.getBlogTitle());
            bl.setBlogContent(blog.getBlogContent());
            bl.setBlogImage(blog.getBlogImage());
            bl.setBlogCreateDate(blog.getBlogCreateDate());
            bl.setBlogStatus(blog.isBlogStatus());
            bl.setUserName(blog.getUser().getUserName());
            bl.setListTag(blog.getListTag());
            bl.setListComment(blog.getListComment());
            listBlogRe.add(bl);
        }
        return listBlogRe;
    }


    ///end getallBlog////

    ////start getBlogByID///
    @GetMapping("/{blogID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Blog findByIDBlog(@PathVariable("blogID") int blogID) {
        return blogService.findByID(blogID);
    }
    ///end GetBlogById///

    //start createBlog///
    
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/createBlog")
    public ResponseEntity<?> create(@RequestBody Blog blogs) {
        try {
            CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.findByUserId(userDetails.getUserId());
            List<Tag> listTag = new ArrayList<>();
            blogs.setUser(user);
            for (Tag tag : blogs.getListTag()) {
                if (tagService.findByTagName(tag.getTagName()) != null) {
                    Tag tagExist = tagService.findByTagName(tag.getTagName());
                    listTag.add(tagExist);
                } else {
                    Tag tagNew = new Tag();
                    tagNew.setTagName(tag.getTagName());
                    tagNew.setTagStatus(true);
                    listTag.add(tagService.saveOrUpdate(tagNew));
                }
            }
            blogs.setBlogCreateDate(java.time.LocalDate.now());
            blogs.setListTag(listTag);
            blogs.setBlogStatus(true);
            blogService.saveOrUpdate(blogs);
            return ResponseEntity.ok("Bạn đã thêm mới thành công blog");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("bạn chưa thêm mới thành công blog😁😁😁");
        }
    }


    //end createBlog///
//start upsate blog///
    @PutMapping("/updateBlog/{blogID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateBlog(@PathVariable("blogID") int blogID, @RequestBody BlogRequest blog) {
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
        } catch (Exception e) {
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
    public List<Blog> searchTitleBlog(@RequestParam("blogTitle") String blogTitle) {
        return blogService.searchByTitle(blogTitle);

    }
    //end searchTitleBlog///

    ///start sortTimeBlog///
    @GetMapping("/sortByDaTimeBlog")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Blog>> sortByDateCreate(@RequestParam("direction") String direction) {
        List<Blog> listBlog = blogService.sortByDateBlog(direction);
        return new ResponseEntity<>(listBlog, HttpStatus.OK);
    }
    //end sortTimeBlog///
//    @GetMapping("/searchTitleAndSort")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public ResponseEntity<List<Blog>> searchTitleAndSort(
//   @RequestParam("direction") String direction,@RequestParam("blogTitle") String blogTitle ){
//        List<Blog> blogList = blogService.searchTitleAndSort(direction,blogTitle);
//        return new ResponseEntity<>(blogList,HttpStatus.OK);
//
//    }

    @GetMapping("/searchTitleAndSort")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> findByTitleAndSort(
            @RequestParam String blogTitle,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam String direction
    ) {
        Sort.Order order;
        if (direction.equals("asc")) {
            order = new Sort.Order(Sort.Direction.ASC, "blogTitle");
        } else {
            order = new Sort.Order(Sort.Direction.DESC, "blogTitle");
        }


        Pageable pageable = PageRequest.of(page, 2, Sort.by(order));
        Page<Blog> pageBlog = blogService.searchTitleAndSort(blogTitle,pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("blogs", pageBlog.getContent());
        data.put("total", pageBlog.getSize());
        data.put("totalItems", pageBlog.getTotalElements());
        data.put("totalPages", pageBlog.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }



}
