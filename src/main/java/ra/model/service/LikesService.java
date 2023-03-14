package ra.model.service;

import ra.model.entity.Comment;
import ra.model.entity.Likes;

import java.util.List;

public interface LikesService {
    Likes save(Likes likes);
    void delete(int likesID);
    Likes findByID(int likeID);
    List<Likes> findAll();
}
