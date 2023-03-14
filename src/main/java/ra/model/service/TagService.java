package ra.model.service;

import org.springframework.stereotype.Service;
import ra.model.entity.Blog;
import ra.model.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();

    Tag findByID(int tagID);

    Tag saveOrUpdate(Tag tag);

    void delete(int tagID);
    Tag findByTagName(String tagName);



}
