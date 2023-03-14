package ra.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.*;
import ra.model.service.ExhibitionService;
import ra.model.service.ProductService;
import ra.model.service.TagService;
import ra.model.service.UserService;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/exhibition")
public class ExhibitionController {
    @Autowired
    private ExhibitionService exhibitionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TagService tagService;

    //start GetAllExhibition///
    @GetMapping("/getAllExgibition")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Exhibition> getAllExhibition() {
        List<Exhibition> exhibitionList = exhibitionService.findAll();

        return exhibitionList;
    }

    //end GetAllExhibition///

    //start GetByIDExhibition///

    @GetMapping("/{exhibitionID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Exhibition findByIdExhibition(@PathVariable("exhibitionID") int exhibitionID) {
        return exhibitionService.findById(exhibitionID);
    }
    //end GetByIDExhibition///

    //start create exhibition//
    @PostMapping("/createExhibition")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createExhibition(@RequestBody Exhibition exhibition){
        try {
            List<Tag> tagList = new ArrayList<>();
            for (int i = 0; i < exhibition.getListTag().size(); i++) {
                if(tagService.findByTagName(String.valueOf(exhibition.getListTag().get(i)))!=null){
                    Tag tagFind = tagService.findByTagName(String.valueOf(exhibition.getListTag().get(i)));
                    tagList.add(tagFind);
                }else {
                    Tag tag = new Tag();
                    tag.setTagName(String.valueOf(exhibition.getListTag().get(i)));
                    tag.setTagStatus(true);
                    tagList.add(tagService.saveOrUpdate(tag));
                }
            }
            exhibition.setExhibitionStatus(true);
            exhibition.setListTag(tagList);
            exhibitionService.saveOfUpdate(exhibition);
            return ResponseEntity.ok("Bạn đã thêm mới exhibition thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Đã có lỗi trong quá trình sử lý vui lòng thử lại lần sau");
        }
    }
    //end createExhibition//

    //start updateExhibition//

    @PutMapping("/updateExhibition/{exhibitionID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateExhibition(@PathVariable("exhibitionID") int exhibitionID,@RequestBody Exhibition exhibition){
        try {
            Exhibition exhibition1 = findByIdExhibition(exhibitionID);
            List<Tag> tagList = new ArrayList<>();
            for (int i = 0; i < exhibition.getListTag().size(); i++) {
                if(tagService.findByTagName(String.valueOf(exhibition.getListTag().get(i)))!=null){
                    Tag tagFind = tagService.findByTagName(String.valueOf(exhibition.getListTag().get(i)));
                    tagList.add(tagFind);
                }else {
                    Tag tag = new Tag();
                    tag.setTagName(String.valueOf(exhibition.getListTag().get(i)));
                    tag.setTagStatus(true);
                    tagList.add(tagService.saveOrUpdate(tag));
                }
            }
            exhibition1.setExhibitionStatus(true);
            exhibition1.setListTag(tagList);
            exhibitionService.saveOfUpdate(exhibition1);
            return ResponseEntity.ok("Bạn đã cập nhật thành công exhibition");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Có lỗi xảy ra trong quá trình sử lý!! vui lòng bạn thử lại sau");
        }
    }

    //end updateExhibition//

//delete///
    @DeleteMapping("/delete/{exhibitionID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteExhibition(@PathVariable("exhibitionID") int exhibitionID) {
        try{
            exhibitionService.findById(exhibitionID);
            exhibitionService.delete(exhibitionID);
            return ResponseEntity.ok("Đã xóa thành công ");
        }catch (Exception e){
            return ResponseEntity.ok("Chưa xóa được kiểm tra lại ");
        }
    }
    /////








}
