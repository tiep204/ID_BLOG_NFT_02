package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Following;
import ra.model.entity.User;
import ra.model.service.FollowingService;
import ra.model.service.UserService;
import ra.security.CustomUserDetail;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/following")
public class FollowingController {
    @Autowired
    private FollowingService followingService;

    @Autowired
    private UserService userService;

    //start GetAllFollowing///
    @GetMapping("/getAllFollowing")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Following> getAllFollowing(){
        return followingService.findAll();
    }
    //end getAllFollowing///

    ///start createfollowing///
    @PostMapping("/createFollow")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createFollow(@RequestBody Following following){
        try {
            CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User users = userService.findByUserId(userDetails.getUserId());
            Following followNew = new Following();
            followNew.setUser(users);
            followNew.setFollowingUser(following.getFollowingUser());
            followNew.setFollowStatus(true);
            followingService.saveOfUpdate(followNew);
            return ResponseEntity.ok("bạn đã follow thành công rồi đó😁😒😁😁");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("bạn đã following thất bại mất rồi");
        }
    }
    ///end create following///

    ///start unfollowing///
    @DeleteMapping("/unfollow/{followID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> unfollow(@PathVariable("followID") int followID){
        try {
            followingService.findById(followID);
            followingService.delete(followID);
            return ResponseEntity.ok("Bạn đã unfollow thành công ");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("bạn chưa unfollow thành công");
        }
    }
    ///end unfollowing///

}
