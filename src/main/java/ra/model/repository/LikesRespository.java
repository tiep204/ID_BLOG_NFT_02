package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Likes;

import java.util.List;

@Repository
public interface LikesRespository extends JpaRepository<Likes,Integer> {
    List<Likes> findByUser_UserID(int userID);
}
