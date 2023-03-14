package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Tag;
import ra.model.repository.TagRespository;
import ra.model.service.TagService;

import java.util.List;

@Service
public class TagServiceImp implements TagService {
    @Autowired
    private TagRespository tagRespository;
    @Override
    public List<Tag> findAll() {
        return tagRespository.findAll();
    }

    @Override
    public Tag findByID(int tagID) {
        return tagRespository.findById(tagID).get();
    }

    @Override
    public Tag saveOrUpdate(Tag tag) {
        return tagRespository.save(tag);
    }

    @Override
    public void delete(int tagID) {
    tagRespository.deleteById(tagID);
    }

    @Override
    public Tag findByTagName(String tagName) {
        return tagRespository.findByTagName(tagName);
    }
}
