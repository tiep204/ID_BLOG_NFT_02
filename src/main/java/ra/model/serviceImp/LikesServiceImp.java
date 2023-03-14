package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Comment;
import ra.model.entity.Likes;
import ra.model.repository.LikesRespository;
import ra.model.service.LikesService;

import java.util.List;

@Service
public class LikesServiceImp implements LikesService {
    @Autowired
    private LikesRespository likesRespository;

    @Override
    public Likes save(Likes likes) {
        return likesRespository.save(likes);
    }

    @Override
    public void delete(int likesID) {
        likesRespository.deleteById(likesID);

    }

    @Override
    public Likes findByID(int likeID) {
        return likesRespository.findById(likeID).get();
    }

    @Override
    public List<Likes> findAll() {
        return likesRespository.findAll();
    }
}
