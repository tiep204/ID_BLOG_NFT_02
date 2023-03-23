package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Following;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Integer> {
}
