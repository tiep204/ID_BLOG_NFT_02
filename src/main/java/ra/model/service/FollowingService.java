package ra.model.service;

import ra.model.entity.Following;

import java.util.List;

public interface FollowingService {
    List<Following> findAll();
    Following findById(int followingID);
    Following saveOfUpdate(Following following);
    void delete(int followingID);

}
