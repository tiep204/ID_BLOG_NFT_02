package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Likes;
@Repository
public interface LikesRespository extends JpaRepository<Likes,Integer> {
}
