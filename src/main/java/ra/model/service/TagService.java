package ra.model.service;

import ra.model.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();

    Tag findByID(int tagID);

    Tag saveOrUpdate(Tag tag);

    Tag delete(int tagID);
    Tag findByTagName(String tagName);



}
