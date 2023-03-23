package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Tag;
import ra.model.service.TagService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/tags")
public class TagsController {
    @Autowired
    private TagService tagService;
    ///start getAllTag///
    @RequestMapping("/getAllTag")
    public List<Tag> getAllTags(){
        return tagService.findAll();
    }
    ///end getAllTag

    ///start deleteTag////
    @DeleteMapping("/deletetags/{tagID}")
    public Tag deleteTag(@PathVariable("tagID") int tagID){
       return tagService.delete(tagID);
    }
    ///end deleteTag///
}
