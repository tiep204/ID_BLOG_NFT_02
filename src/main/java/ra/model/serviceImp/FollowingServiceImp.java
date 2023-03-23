package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Following;
import ra.model.repository.FollowingRepository;
import ra.model.service.FollowingService;

import java.util.List;

@Service
public class FollowingServiceImp implements FollowingService {
    @Autowired
    private FollowingRepository followingRepository;

    @Override
    public List<Following> findAll() {
        return followingRepository.findAll();
    }

    @Override
    public Following findById(int followingID) {
        return followingRepository.findById(followingID).get();
    }

    @Override
    public Following saveOfUpdate(Following following) {
        return followingRepository.save(following);
    }

    @Override
    public void delete(int followingID) {
        followingRepository.deleteById(followingID);
    }
}
