package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.*;
import ra.model.service.BlogService;
import ra.model.service.LikesService;
import ra.model.service.UserService;
import ra.payload.request.LikesRequest;
import ra.security.CustomUserDetail;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/likes")
public class likeController {
    @Autowired
    private UserService userService;
    @Autowired
    private LikesService likesService;
    @Autowired
    private BlogService blogService;
    ///start getAllLikes///
    @GetMapping("/getAllLike")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Likes> getAllLikes() {
        return likesService.findAll();
    }
    /////end getAllLikes//////

         ///start createLikes////
    @PostMapping("/createLikes")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createLikes(@RequestBody LikesRequest likes){
        try {
            CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User users = userService.findByUserId(userDetails.getUserId());
            Blog blog = blogService.findByID(likes.getBlogID());
            Likes likes1 = new Likes();
            likes1.setUser(users);
            likes1.setBlog(blog);
            likes1.setLikeStatus(true);
            List<Likes> listLikes = likesService.findByUser_UserID(users.getUserID());
            for (Likes like: listLikes) {
                if (like.getBlog().getBlogID()==blog.getBlogID()){
                    return ResponseEntity.ok("ban da like bai nay ");
                }
            }
            likesService.save(likes1);
            return ResponseEntity.ok("like thành công😘😛😛😛");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("like khong thanh cong");
        }
    }

        /////end createLikes////



        /////start deleteLikes////

    @PostMapping("/deleteLikes/{likeID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteLikes(@PathVariable("likeID") int likeID){
        try {
            likesService.findByID(likeID);
            likesService.delete(likeID);
            return ResponseEntity.ok("Bạn đã xóa like thành công 😘😘😘");
        } catch (Exception e) {
            return ResponseEntity.ok("Đã có vấn đề trong quá trình xóa");
        }
    }
    /////end deleteLikes////






}
